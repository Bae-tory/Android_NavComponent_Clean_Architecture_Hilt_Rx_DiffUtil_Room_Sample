package com.baetory.data.mapper.book

import com.baetory.data.mapper.EntityMapper
import com.baetory.data.model.BookDataModel
import com.baetory.data.model.BookPagingMetaDataModel
import com.baetory.data.model.BookSearchDataModel
import com.baetory.domain.entity.book.BookEntity
import com.baetory.domain.entity.book.BookInfo
import com.baetory.domain.entity.book.PageMetaData
import javax.inject.Inject

class BookEntityMapper @Inject constructor(
    private val bookInfoEntityMapper: BookInfoEntityMapper
) : EntityMapper<BookSearchDataModel, BookEntity> {

    override fun toEntity(dataModel: BookSearchDataModel): BookEntity =
        with(dataModel) {
            BookEntity(
                books = bookInfoEntityMapper.toEntities(dataModels = bookDatas),
                pageMeta = with(pagingMeta) {
                    PageMetaData(
                        isEnd = isEnd,
                        pageableCount = pageableCount,
                        totalCount = totalCount
                    )
                }
            )

        }

    override fun toDataModel(entity: BookEntity): BookSearchDataModel =
        with(entity) {
            BookSearchDataModel(
                bookDatas = bookInfoEntityMapper.toDataModels(entities = books),
                pagingMeta =
                if (pageMeta == null) {
                    BookPagingMetaDataModel(false, 0, 0)
                } else {
                    with(pageMeta) {
                        BookPagingMetaDataModel(
                            isEnd = this!!.isEnd,
                            pageableCount = this.pageableCount,
                            totalCount = this.totalCount
                        )
                    }
                }
            )
        }

    fun toBookInfo(dataModel: BookDataModel): BookInfo =
        BookInfo(
            authors = dataModel.authors,
            translators = dataModel.translators,
            isEnd = dataModel.isEnd,
            contents = dataModel.contents,
            dateTime = dataModel.dateTime,
            bookNumber = dataModel.bookNumber,
            price = dataModel.price,
            publisher = dataModel.publisher,
            salePrice = dataModel.salePrice,
            saleStatus = dataModel.saleStatus,
            thumbnailImageUrl = dataModel.thumbnailImageUrl,
            title = dataModel.title,
            bookDetailUrl = dataModel.bookDetailUrl
        )
}

class BookInfoEntityMapper @Inject constructor() : EntityMapper<BookDataModel, BookInfo> {
    override fun toEntity(dataModel: BookDataModel): BookInfo =
        with(dataModel) {
            BookInfo(
                id = id,
                authors = authors,
                translators = translators,
                contents = contents,
                isEnd = isEnd,
                dateTime = dateTime,
                bookNumber = bookNumber,
                price = price,
                publisher = publisher,
                salePrice = salePrice,
                saleStatus = saleStatus,
                thumbnailImageUrl = thumbnailImageUrl,
                title = title,
                bookDetailUrl = bookDetailUrl
            )
        }

    override fun toDataModel(entity: BookInfo): BookDataModel =
        with(entity) {
            BookDataModel(
                id = id,
                authors = authors,
                translators = translators,
                contents = contents,
                dateTime = dateTime,
                isEnd = isEnd,
                bookNumber = bookNumber,
                price = price,
                publisher = publisher,
                salePrice = salePrice,
                saleStatus = saleStatus,
                thumbnailImageUrl = thumbnailImageUrl,
                title = title,
                bookDetailUrl = bookDetailUrl
            )
        }
}

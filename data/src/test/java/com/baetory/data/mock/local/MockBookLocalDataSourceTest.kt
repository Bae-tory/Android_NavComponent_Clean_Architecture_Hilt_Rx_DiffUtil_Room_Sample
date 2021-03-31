package com.baetory.data.mock.local

import com.baetory.data.exception.DatabaseException
import com.baetory.data.mock.mockdata.MockData
import com.baetory.data.model.BookDataModel
import com.baetory.data.source.local.BookLocalDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class MockBookLocalDataSourceTest : BookLocalDataSource {

    private val bookDataModel = MockData.bookDataModel
    private val bookDataModels = MockData.bookDataModels.toMutableList()

    override fun insert(dataModel: BookDataModel): Completable =
        if (bookDataModels.any { it.id == dataModel.id }.not()) {
            bookDataModels.add(dataModel)
            Completable.complete()
        } else {
            Completable.error(DatabaseException.DuplicatedException("$dataModel is duplicated"))
        }

    override fun insert(dataModels: List<BookDataModel>): Completable = run {
        dataModels.map { it.id }
            .forEachIndexed { index, newId ->
                if (bookDataModels.any { it.id == newId })
                    return@run Completable.error(DatabaseException.DuplicatedException("${dataModels[index]} is duplicated"))
            }

        bookDataModels.addAll(dataModels)
        Completable.complete()
    }

    override fun selectAll(): Single<List<BookDataModel>> =
        Single.just(bookDataModels)

    override fun selectById(id: Int): Single<BookDataModel> =
        if (bookDataModels.any { it.id == id }) {
            Single.just(bookDataModels.find { it.id == id })
        } else {
            Single.error(DatabaseException.NotFoundException("there is no dataModel id is $id"))
        }

    override fun update(dataModel: BookDataModel): Completable =
        if (bookDataModels.any { it.id == dataModel.id }) {
            val index = bookDataModels.indexOfFirst { it.id == dataModel.id }
            bookDataModels[index] = dataModel
            Completable.complete()
        } else {
            Completable.error(DatabaseException.NotFoundException("result is empty"))
        }

    override fun deleteById(id: Int): Completable = run {
        if (bookDataModels.any { it.id == id }) {
            val index = bookDataModels.indexOfFirst { it.id == id }
            bookDataModels.removeAt(index)
            Completable.complete()
        } else {
            Completable.error(DatabaseException.NotFoundException("there is no dataModel id is $id"))
        }
    }

    override fun drop(): Completable = run {
        bookDataModels.clear()
        Completable.complete()
    }

    override fun hasItem(id: Int): Single<Boolean> =
        Single.just(bookDataModels.any { it.id == id })

    override fun selectAllCount(): Single<Int> =
        Single.just(bookDataModels.count())
}
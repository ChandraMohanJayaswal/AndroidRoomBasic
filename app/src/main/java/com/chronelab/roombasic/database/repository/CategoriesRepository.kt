package com.chronelab.roombasic.database.repository


import com.chronelab.roombasic.database.dao.CategoryDao
import com.chronelab.roombasic.database.entity.Category
import kotlinx.coroutines.flow.Flow

class CategoriesRepository(private val categoryDao: CategoryDao): CategoryiesRepositoryInterface {

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
    }

    override fun getCategory(id: Int): Flow<Category?> {
        return categoryDao.getCategory(id)
    }

    override suspend fun insertCategory(category: Category) {
        categoryDao.insert(category)
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.delete(category)
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.update(category)
    }

    override fun getCategory(name:String): Flow<Category?> {
        return categoryDao.getCategory(name)
    }

}
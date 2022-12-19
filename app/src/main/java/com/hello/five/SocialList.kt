package com.hello.five

abstract class SocialList {
    protected var nextId = 1

    protected var itemList = ArrayList<Student>()

    //region Фильтрация
    fun display(filter: ListFilter): String {
        var output = ""
        itemList
            .filter {
                when (filter) {
                    ListFilter.All -> true
                    ListFilter.Social -> it.socialized
                    ListFilter.Unsocial -> !it.socialized
                }
            }
            .forEach {
                output += "$it\n"
            }
        if (output == "") {
            output = "Нет студентов\n"
        }
        return output
    }
    //endregion
    enum class ListFilter {
        All, Social, Unsocial
    }
}
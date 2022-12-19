package com.hello.five

class StudentList : SocialList() {

    //region Добавление студента
    fun addStudent(name: String) {
        val student = Student(nextId, name)
        nextId++
        itemList.add(student)
    }
    //endregion
    fun removeStudent(id: Int){
        val student = itemList.find { it.id == id } ?: throw Error()
        itemList.remove(student)
    }
    fun editStudent(id: Int, name: String){
        itemList.find { it.id == id } ?: throw Error()
        val studentEdit = Student(id, name)
        itemList[id-1] = studentEdit
    }
    fun sortStudent():String {
        val sort = itemList.sortedBy { it.name }
        return sort.toString()
    }

    //endregion
    fun searchStudent(name: String): String {
        val student = itemList.find { it.name == name } ?: throw Error()
        return student.toString()
    }
    //region Функция присвоения социальной стипендии
    fun markStudentSoc(id: Int, soc: Boolean) {
        val studentSoc = itemList.find { it.id == id } ?: throw Error()
        if (soc)
            studentSoc.social()
        else
            studentSoc.unsocial()
    }
    //endregion
}
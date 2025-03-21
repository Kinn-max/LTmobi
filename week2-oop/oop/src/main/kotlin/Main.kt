package org.example

import java.util.Objects


fun option(){
    println("1. Tao danh sach nguoi dung")
    println("2. Tao danh sach sach")
    println("3. Muon sach va hien thi")
}
fun createListUser(listUser: MutableList<Student>){
    println("Nhập số lượng người dùng muốn tạo:")
    val n = readLine()!!.toInt()

    for (i in 0 until n) {
        println("Nhập ID sinh viên thứ ${i + 1}:")
        val id = readLine()?.toIntOrNull() ?: 0
        println("Nhập tên sinh viên:")
        val name = readLine() ?: "Không có tên"
        println("Nhập tuổi sinh viên:")
        val age = readLine()?.toIntOrNull() ?: 18
        listUser.add(Student(name, age, id))
    }
}
fun createListBook (listBook: MutableList<Book>){
    println("Nhập số lượng sách muốn tạo")
    val n = readLine()!!.toInt()

    for (i in 0 until n) {
        println("Nhập tên sách thứ ${i + 1}:")
        val name = readLine() ?: "Không có tên"
        println("Nhập tên tác giả của sách thứ ${i + 1}:")
        val author = readLine() ?: "Không có tên"
        listBook.add(Book(name,author))
    }

}
fun showListBook(listBook: MutableList<Book>){
    println("Danh sách sách hiện có:")
    if (listBook.isEmpty()) {
        println("Chưa có sách")
        return
    }
    for (book in listBook) {
        book.display()
    }
}

fun borrowById(
    listBook: MutableList<Book>,
    listUser: MutableList<Student>,
    listBorrowed: MutableList<Map<Student, Book>>
) {
    println("Bạn muốn mượn sách thứ: ")
    val stt = readLine()?.toIntOrNull()?.minus(1)
    if (stt == null || stt !in listBook.indices) {
        println("Số thứ tự sách không hợp lệ!")
        return
    }
    println("Nhập ID sinh viên muốn mượn sách:")
    val ID = readLine()?.toIntOrNull()
    val std = listUser.find { it.getId() == ID }
    if (std == null) {
        println("Không tìm thấy sinh viên với ID: $ID")
        return
    }
    val book = listBook[stt]
    listBorrowed.add(mapOf(std to book))
    println("Sinh viên ${std.name} đã mượn sách: ${book.name}")
}

fun showBorrowed(listBorrowed: MutableList<Map<Student, Book>>) {
    println("Danh sách sinh viên đã mượn sách:")

    if (listBorrowed.isEmpty()) {
        println("Hiện chưa có sinh viên nào mượn sách.")
        return
    }

    for (borrowEntry in listBorrowed) {
        for ((student, book) in borrowEntry) {
            println("Sinh viên: ${student.name} (ID: ${student.getId()}) đã mượn sách: ${book.displayInfo()}")
        }
    }
}



fun main() {
    option()
    var listUser = mutableListOf<Student>()
    var listBook = mutableListOf<Book>()
    var listBorrowed = mutableListOf<Map<Student,Book>>()
    while (true){
    println("Enter options: ")
    val option = readlnOrNull() ?: ""
        when (option) {
            "1" -> {
                createListUser(listUser)
            }
            "2" -> {
                createListBook(listBook)
            }
            "3" -> {
                showListBook(listBook)
                borrowById(listBook, listUser, listBorrowed)
            }

            else -> break
        }
    }
}


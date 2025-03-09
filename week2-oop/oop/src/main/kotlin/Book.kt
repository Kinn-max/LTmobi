package org.example

class Book {
    var name: String
    var author: String

    constructor(name: String, author: String) {
        this.name = name
        this.author = author
    }
    fun  displayInfo() {
        println( "Thông tin sách ${this.name} của tác giả ${this.author}")
    }
    fun  display(){
        println("Tên sách: $name, Tác giả: $author")
    }
}
package org.example

class Student(name: String, age: Int, val studentId: Int) : User(name, age) {
    fun displayStudentInfo() {
        println("Student ID: $studentId")
        println("Name : $name")
        println("Age : $age")
    }
    fun getId(): Int {
        return studentId
    }
}

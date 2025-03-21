package com.example.nodemanager.repository


interface MainLog {
    fun i(tag: String, msg:String)
    fun d(tag: String, msg:String)
    fun e(tag: String, msg:String)
}
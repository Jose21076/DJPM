package com.example.shoppinglist

data class ListItems(var docID: String?, var name: String?, var owners: List<String>?) {

    constructor() : this(null, null, null)
}
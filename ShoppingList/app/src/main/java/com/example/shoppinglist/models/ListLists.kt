package com.example.shoppinglist.models

data class ListLists(var docID: String?,
                     var name: String?,
                     var owners: List<String>?) {
    constructor() : this(null, null, null)
}
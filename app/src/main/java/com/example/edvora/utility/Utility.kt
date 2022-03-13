package com.example.edvora.utility

import com.example.edvora.model.RidesCollectionModel

class Utility {

    companion object {
        fun appendRidesCollectionModel(array: Array<RidesCollectionModel?>, element: RidesCollectionModel): Array<RidesCollectionModel?> {
            val localArray = array.copyOf(array.size + 1)
            localArray[array.size] = element
            return localArray
        }

        fun appendToStringArray(array: Array<String?>, element: String): Array<String?> {
            val localArray = array.copyOf(array.size + 1)
            localArray[array.size] = element
            return localArray
        }
    }
}



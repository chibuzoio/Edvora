package com.example.edvora.utility

import com.example.edvora.model.RidesCollectionModel

class Utility {

    companion object {
        fun append(array: Array<RidesCollectionModel?>, element: RidesCollectionModel): Array<RidesCollectionModel?> {
            val localArray = array.copyOf(array.size + 1)
            localArray[array.size] = element
            return localArray
        }
    }
}



package com.example.edvora.model

import kotlinx.serialization.Serializable

@Serializable
data class RidesCollectionModel(var id: Int,
                                var origin_station_code: Int,
                                var station_path: ArrayList<Int>,
                                var destination_station_code: Int,
                                var date: String,
                                var map_url: String,
                                var state: String,
                                var city: String)

/*
* {
* "id":656,
* "origin_station_code":19,
* "station_path":[41,57,69,74,80],
* "destination_station_code":97,
* "date":"02/28/2022 03:50 PM",
* "map_url":"https://picsum.photos/200",
* "state":"Odisha",
* "city":"Jharsuguda"
* }
* */



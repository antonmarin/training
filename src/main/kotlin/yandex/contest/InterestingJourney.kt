package yandex.contest

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.abs

// WA in test 15
private val input = BufferedReader(InputStreamReader(System.`in`))
fun main() {
    val inputData = parseInput(input)
    val graph = Graph(inputData.cities, inputData.maxDistance)
    println(graph.bfs(inputData.fromCityId, inputData.toCityId))
}


data class City(val cityId: Int, val x: Int, val y: Int)
data class InputData(
    val cities: List<City>,
    val maxDistance: Int,
    val fromCityId: CityId,
    val toCityId: CityId,
)
typealias CityId = Int

fun countDistanceBetween(cityFrom: City, cityTo: City): Int {
    return abs(cityTo.x - cityFrom.x) + abs(cityTo.y - cityFrom.y)
}

fun parseInput(reader: BufferedReader): InputData {
    val citiesCount = reader.readLine()!!.toInt()
    val cities = mutableListOf<City>()
    for (i in 1..citiesCount) {
        cities.add(reader.readLine()!!.split(' ').let { City(i, it[0].toInt(), it[1].toInt()) })
    }
    val maxDistance = reader.readLine()!!.toInt()
    val (fromCity, toCity) = reader.readLine()!!.split(' ').map { it.toInt() }

    return InputData(cities, maxDistance, fromCity, toCity)
}

class Graph(private val cities: List<City>, maxDistance: Int) {
    val edges: Map<CityId, List<CityId>>

    init {
        val edgesFromTo = HashMap<CityId, MutableList<CityId>>()
        cities.forEach { fromCity ->
            cities.forEach { toCity ->
                if (fromCity != toCity && countDistanceBetween(fromCity, toCity) <= maxDistance) {
                    if (edgesFromTo[fromCity.cityId] == null) {
                        edgesFromTo[fromCity.cityId] = mutableListOf()
                    }
                    edgesFromTo[fromCity.cityId]!!.add(toCity.cityId)
                }
            }
        }
        edges = edgesFromTo
    }

    fun bfs(fromCityId: CityId, toCityId: CityId): Int {
        val queue: Queue<CityId> = LinkedList()
        val visited = mutableSetOf<CityId>()
        visited.add(fromCityId)
        val roadsCntTo = HashMap<CityId,Int>()
        roadsCntTo[fromCityId] = 0

        var current: CityId? = fromCityId
        while (current != null) {
            if(current == toCityId) return roadsCntTo[current]!!
            edges[current]?.forEach { target ->
                if (target !in visited) {
                    roadsCntTo[target] = roadsCntTo[current]!! + 1
                    queue.add(target)
                    visited.add(target)
                }
            }
            current = queue.poll()
        }

        return -1
    }
}

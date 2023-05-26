package signature

import java.io.File


fun main() {
    print("Enter name and surname: ")
    val name = readln().trim()
    print("Enter person's status: ")
    val status = readln().lowercase().trim()
    val rom = mapIt(File("src/roman.txt").readText().split("\n") as MutableList<String>)
    val med = mapIt(File("src/medium.txt").readText().split("\n") as MutableList<String>)
    val romSize = rom.values.toList()[0].size
    val romSpace = rom.values.toList()[0][0].length
    val roman = MutableList(romSize) { "" }
    for (i in name) {
        for (j in 0 until romSize) {
            roman[j] += when (i) {
                ' ' -> " ".repeat(romSpace)
                else -> rom[i]?.get(j) ?: ""
            }
        }
    }
    val medSize = med.values.toList()[0].size
    val medSpace = med.values.toList()[0][0].length
    val medium = MutableList(medSize) { "" }
    for (i in status) {
        for (j in 0 until medSize) {
            medium[j] += when (i) {
                ' ' -> " ".repeat(medSpace)
                else -> med[i]?.get(j) ?: ""
            }
        }
    }
    val romanName = roman.map { "  $it  " }.toTypedArray()[0].length
    val medStat = medium.map { "  $it  " }.toTypedArray()[0].length
    val padSides = { array: Array<String> -> (array.map { "88${it}88" }).toTypedArray() }
    val b = romanName % 2 != 0 && medStat % 2 != 0
    val padName =
        if (romanName >= medStat) padSides(roman.map { "  $it  " }.toTypedArray()) else (roman.map { "  $it  " }
            .toTypedArray().map {
                "88" + " ".repeat(((medStat) / 2) - (if (b) 0 else romanName % 2) - (romanName / 2)) + it +
                        " ".repeat(((medStat) / 2) + (if (b) 0 else medStat % 2) - (romanName / 2)) + "88"
            }).toTypedArray()
    val padStat =
        if (medStat >= romanName) padSides(medium.map { "  $it  " }.toTypedArray()) else (medium.map { "  $it  " }
            .toTypedArray().map {
                "88" + " ".repeat(((romanName) / 2) - (if (b) 0 else medStat % 2) - (medStat / 2)) + it +
                        " ".repeat(((romanName) / 2) + (if (b) 0 else romanName % 2) - (medStat / 2)) + "88"
            }).toTypedArray()

    println("8".repeat(padStat[0].length))
    for (i in padName) {
        println(i)
    }
    for (i in padStat) {
        println(i)
    }
    println("8".repeat(padStat[0].length))


}


fun mapIt(mThis: MutableList<String>): Map<Char, MutableList<String>> {
    val size = mThis[0].split(" ")[0].toInt()
    var count = 0
    val mapLetter = mutableMapOf<Char, MutableList<String>>()
    var fill = ' '

    for (i in 1..mThis.lastIndex) {
        if (count == 0) {
            fill = mThis[i][0]
            mapLetter[fill] = MutableList(size) { "" }
        } else if (count in 1..size) {
            mapLetter[fill]?.set(count - 1, (mThis[i]))
            if (count == size) count = -1
        }
        count++
    }
    return mapLetter
}

























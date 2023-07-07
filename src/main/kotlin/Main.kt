fun main(args: Array<String>) {

    val listOfArchives: MutableList<Archive> = ArrayList()
    val listOfNotes: MutableList<Note> = ArrayList()

    Menu("Главное меню", listOfArchives, listOfNotes, 0).executeMenu()

}
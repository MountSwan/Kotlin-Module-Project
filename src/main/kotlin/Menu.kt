import java.util.Scanner

class Menu(
    private val type: String,
    private val listOfArchives: MutableList<Archive>,
    private val listOfNotes: MutableList<Note>,
    private val numberOfObject: Int
) {
    private var number: Int = 0
    private var titleOfObject: String = ""
    private var textOfNote: String = ""

    private fun pause() {
        println("\n\nДля возврата в меню введите любой символ")
        Scanner(System.`in`).nextLine()
    }

    private fun printMenu(listSize: Int, titleOfScreen: String) {
        println(titleOfScreen)
        if (listSize > 0) {
            for (i in 1..listSize) {
                if (type == "Главное меню") {
                    println("${i + 1}. Выбрать архив \"${listOfArchives[i - 1].title}\"")
                } else {
                    println("${i + 1}. Выбрать заметку \"${listOfNotes[i - 1].title}\"")
                }
            }
        }
        println("${listSize + 2}. Выход.")
    }

    private fun getNumber(listSize: Int) {
        var numberString: String

        while (true) {
            println("Выберите пункт меню, который нужно выполнить")
            numberString = Scanner(System.`in`).nextLine()
            try {
                number = numberString.toInt()
                if ((number > 0) and (number < listSize + 3)) {
                    break
                } else {
                    println("Ошибка ввода. Число должно быть в диапазоне от 1 до ${listSize + 2}.")
                }
            } catch (nfe: NumberFormatException) {
                println("Ошибка ввода. Необходимо ввести целое число в диапазоне от 1 до ${listSize + 2}.")
            }
        }
    }

    fun executeMenu() {
        var listSize: Int
        var titleOfScreen1: String
        var titleOfScreen2: String

        while (true) {
            if (type == "Главное меню") {
                listSize = listOfArchives.size
                titleOfScreen1 = "\n\nЭкран главного меню\n\n1. Создать архив."
                titleOfScreen2 = "\n\nЭкран создания архива\n\nВведите название архива"
            } else {
                listSize = listOfNotes.size
                titleOfScreen1 = "\n\nЭкран просмотра архива \"${listOfArchives[numberOfObject].title}\"\n\n1. Создать заметку."
                titleOfScreen2 = "\n\nЭкран создания заметки\n\nВведите название заметки"
            }

            printMenu(listSize, titleOfScreen1)
            getNumber(listSize)

            if (number == 1) {
                println(titleOfScreen2)
                titleOfObject = Scanner(System.`in`).nextLine()
                if (type == "Главное меню") {
                    listOfArchives.add(Archive(titleOfObject, ArrayList()))
                    println("Создан архив \"$titleOfObject\"")
                } else {
                    println("\nВведите текст заметки")
                    textOfNote = Scanner(System.`in`).nextLine()
                    listOfNotes.add(Note(titleOfObject, textOfNote))
                    println("Создана заметка \"$titleOfObject\"")
                }
                pause()
            } else {
                if (listSize == 0) {
                    break
                } else {
                    if (number == listSize + 2) {
                        break
                    } else {
                        if (type == "Главное меню") {
                            Menu("Второе меню", listOfArchives, listOfArchives[number - 2].listOfNotes, number - 2).executeMenu()
                        } else {
                            println("\n\nЭкран просмотра заметки \"${listOfArchives[numberOfObject].listOfNotes[number - 2].title}\"")
                            println("\nСодержание\n\n${listOfArchives[numberOfObject].listOfNotes[number - 2].content}")
                            pause()
                        }
                    }
                }
            }
        }
    }
}
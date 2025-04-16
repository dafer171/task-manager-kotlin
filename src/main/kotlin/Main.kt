// Define a data class to represent a Task
data class Task(val id: Int, val name: String, val description: String, val completed: Boolean)

fun main() {
    println("Welcome to the Task Manager ")

    val tasks = mutableListOf<Task>()
    var idCurrent = 1

    while (true) {
        showMenu()

        when (readLine()?.toIntOrNull()) {
            1 -> idCurrent = addTask(tasks, idCurrent)
            2 -> showTasks(tasks)
            3 -> deleteTask(tasks, idCurrent)
            4 -> completeTask(tasks)
            5 -> showCompletedTasks(tasks)
            6 -> {
            println("Exiting the app")
            break
            }
            else -> println("No valid option")
        }
    }
}

// Function to display the menu options
 fun showMenu() {
     println("\nChoose an option: ")
     println("1. Add Task")
     println("2. Show Tasks")
     println("3. Delete Task")
     println("4. Mark as completed a Task")
     println("5. Show completed Tasks")
     println("6. Exit")
 }

// Function to add a new task
fun addTask(tasks: MutableList<Task>, idCurrent: Int): Int{
    print("Write down the task name: ")
    val name = readLine() ?: ""
    print("Write down the task description: ")
    val description = readLine() ?: ""
    val completed = false
    tasks.add(Task(idCurrent,name, description, completed))
    println("Task added")
    return idCurrent +1 // Return the next available ID
}

// Function to display all tasks
fun showTasks(tasks: MutableList<Task>) {
    if (tasks.isEmpty()) {
        println("There are no tasks")
    } else {
        println("Task List:")
        for (task in tasks) {
            println("ID: ${task.id}, Name: ${task.name}, Description: ${task.description}, Completed: ${task.completed}.")
        }
    }
}

// Function to delete a task by its ID
fun deleteTask(tasks: MutableList<Task>, idCurrent: Int) {
    print("Type the task's ID to delete: ")
    val idToDelete = readLine()?.toIntOrNull()
    val deletedTask = tasks.removeIf { it.id == idToDelete }
    if (deletedTask) {
        println("Task Deleted")
    } else {
        println("There is not any task with that ID")
    }
}

// Function to mark a task as completed
fun completeTask(tasks: MutableList<Task>) {
    print("Type the task's ID to mark as completed: ")
    val idToComplete = readLine()?.toIntOrNull()

    if(idToComplete != null ) {
        val taskIndex = tasks.indexOfFirst { it.id == idToComplete } // Find index of the task by ID
        if(taskIndex != -1 ) {
            val task = tasks[taskIndex]
            if (!task.completed) {
                val updatedTask = task.copy(completed = true) // Create a copy with completed = true
                tasks[taskIndex] = updatedTask // Replace the old task
                println("Task marked as completed")
            } else {
                println("Task was already completed")
            }
        } else {
            println("No task found with that ID")
        }
    } else {
        println("Invalid ID")
    }
}

// Function to filter the completed tasks
fun showCompletedTasks(tasks: MutableList<Task>) {
    val completedTasks = tasks.filter { it.completed } //filter tasks
    if (completedTasks.isEmpty()) {
        println("No completed tasks")
    } else {
        println("Completed Tasks")
        completedTasks.forEach { println("ID: ${it.id}, Name: ${it.name}, Description: ${it.description}") }
    }
}

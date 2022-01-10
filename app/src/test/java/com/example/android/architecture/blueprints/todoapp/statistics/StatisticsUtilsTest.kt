package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class StatisticsUtilsTest {

    // No completed, return %100 & %0
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnHundredZero() {

        // Create an active task
        val tasks = listOf<Task>(
            Task("t1", "d1", false),
            Task("t2", "d2", false)
        )

        // Call the function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(result.completedTasksPercent, 0f)
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    // No Active, return %0 & %100
    @Test
    fun getActiveAndCompletedStats_noActive_returnZeroHundred() {

        val tasks = listOf<Task>(Task("t2", "d2", true))
        val result = getActiveAndCompletedStats(tasks)

        assertEquals(result.completedTasksPercent, 100f)
        assertEquals(result.activeTasksPercent, 0f)
    }

    // Both, return %40 & %60
    @Test
    fun getActiveAndCompletedStats_bothActiveAndCompleted_returnFourtySixty() {

        val tasks = listOf(
            Task("t3", "d3", false),
            Task("t4", "d4", false),
            Task("t5", "d5", true),
            Task("t6", "d6", true),
            Task("t7", "d7", true),
        )
        val result = getActiveAndCompletedStats(tasks)

        assertThat(result.activeTasksPercent, `is`(40f))
        assertThat(result.completedTasksPercent, `is`(60f))
    }

    // Error, return 0s
    @Test
    fun getActiveAndCompletedStats_error_returnZero() {

        val result = getActiveAndCompletedStats(null)

        assertEquals(result.activeTasksPercent, 0f)
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    // Empty, return 0s
    @Test
    fun getActiveAndCompletedStats_empty_returnZero() {

        val result = getActiveAndCompletedStats(emptyList())

        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }
}
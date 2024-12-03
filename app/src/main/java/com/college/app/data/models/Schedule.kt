package com.college.app.data.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "schedule", strict = false)
data class Schedule(
    @field:ElementList(name = "classes", inline = true)
    var classes: List<ClassSchedule> = emptyList()
)

@Root(name = "class", strict = false)
data class ClassSchedule(
    @field:Element(name = "courseId")
    var courseId: String = "",

    @field:Element(name = "courseName")
    var courseName: String = "",

    @field:Element(name = "day")
    var day: String = "",

    @field:Element(name = "startTime")
    var startTime: String = "",

    @field:Element(name = "endTime")
    var endTime: String = "",

    @field:Element(name = "room")
    var room: String = "",

    @field:Element(name = "type")
    var type: String = "" // "lecture" or "lab"
) 
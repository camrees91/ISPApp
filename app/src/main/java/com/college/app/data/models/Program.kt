package com.college.app.data.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "program", strict = false)
data class Program(
    @field:Element(name = "id")
    var id: String = "",

    @field:Element(name = "name")
    var name: String = "",

    @field:ElementList(name = "courses", inline = true, required = false)
    var courses: List<Course> = emptyList()
)

@Root(name = "course", strict = false)
data class Course(
    @field:Element(name = "id")
    var id: String = "",

    @field:Element(name = "name")
    var name: String = "",

    @field:Element(name = "lectures")
    var lectures: Int = 0,

    @field:Element(name = "labs")
    var labs: Int = 0,

    @field:Element(name = "credits")
    var credits: Int = 0
) 
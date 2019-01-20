package com.example.turbobudzik;


public class Lesson
{
    public static final String TABLE_NAME = "lessons";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ROOM = "room";
    public static final String COLUMN_COURSETYPE = "courseType";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_SEMESTER = "semester";
    public static final String COLUMN_FACULTY = "faculty";
    public static final String COLUMN_FACULTYABBREVIATION = "facultyAbbreviation";
    public static final String COLUMN_FIELDOFSTUDY = "fieldOfStudy";
    public static final String COLUMN_RESERVATIONSTATUS = "reservationStatus";
    public static final String COLUMN_RESERVATIONSTATUSABBREVIATION = "reservationStatusAbbreviation";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_GROUP = "stu_group";
    public static final String COLUMN_FROM = "date_from";
    public static final String COLUMN_TO = "date_to";
    public static final String COLUMN_ACADEMICTITLE = "academicTitle";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";

    private int id;
    private String room;
    private String courseType;
    private String subject;
    private String semester;
    private String faculty;
    private String facultyAbbreviation;
    private String fieldOfStudy;
    private String reservationStatus;
    private String reservationStatusAbbreviation;
    private String status;
    private String group;

    // Datetime
    private String from;
    private String to;

    // Teacher
    private String academicTitle;
    private String name;
    private String surname;

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ROOM + " TEXT NOT NULL,"
            + COLUMN_COURSETYPE + " TEXT NOT NULL,"
            + COLUMN_SUBJECT + " TEXT NOT NULL,"
            + COLUMN_SEMESTER + " TEXT NOT NULL,"
            + COLUMN_FACULTY + " TEXT NOT NULL,"
            + COLUMN_FACULTYABBREVIATION + " TEXT NOT NULL,"
            + COLUMN_FIELDOFSTUDY + " TEXT NOT NULL,"
            + COLUMN_RESERVATIONSTATUS + " TEXT NOT NULL,"
            + COLUMN_RESERVATIONSTATUSABBREVIATION + " TEXT NOT NULL,"
            + COLUMN_STATUS + " TEXT NOT NULL,"
            + COLUMN_GROUP + " TEXT NOT NULL,"
            + COLUMN_FROM + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
            + COLUMN_TO + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
            + COLUMN_ACADEMICTITLE + " TEXT NOT NULL,"
            + COLUMN_NAME + " TEXT NOT NULL,"
            + COLUMN_SURNAME + " TEXT NOT NULL"
            + ");";

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRoom()
    {
        return room;
    }

    public void setRoom(String room)
    {
        this.room = room;
    }

    public String getCourseType()
    {
        return courseType;
    }

    public void setCourseType(String courseType)
    {
        this.courseType = courseType;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getSemester()
    {
        return semester;
    }

    public void setSemester(String semester)
    {
        this.semester = semester;
    }

    public String getFaculty()
    {
        return faculty;
    }

    public void setFaculty(String faculty)
    {
        this.faculty = faculty;
    }

    public String getFacultyAbbreviation()
    {
        return facultyAbbreviation;
    }

    public void setFacultyAbbreviation(String facultyAbbreviation)
    {
        this.facultyAbbreviation = facultyAbbreviation;
    }

    public String getFieldOfStudy()
    {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy)
    {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getReservationStatus()
    {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus)
    {
        this.reservationStatus = reservationStatus;
    }

    public String getReservationStatusAbbreviation()
    {
        return reservationStatusAbbreviation;
    }

    public void setReservationStatusAbbreviation(String reservationStatusAbbreviation)
    {
        this.reservationStatusAbbreviation = reservationStatusAbbreviation;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getGroup()
    {
        return group;
    }

    public void setGroup(String group)
    {
        this.group = group;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public String getAcademicTitle()
    {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle)
    {
        this.academicTitle = academicTitle;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }
}
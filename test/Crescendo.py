from Orange.data import Table, Domain, ContinuousVariable
from pyodbc import connect

# "table1" from a MySQL server via ODBC connector.
connecteur = connect(DSN='allForYou')
cursur = connecteur.cursor()  # Creation of the cursor for data swept.

# Execution of the SQL Query.
cursur.execute("SELECT * FROM `forDataCompetition`.`schools`;")

# All data of `forDataCompetition`.`schools` are saved in "schools".
schools = cursur.fetchall()


list_forLongLive = []  # Final list : It will contain the final data.


# Whereas having tuples as list elements, you must convert them into lists included into the main list.
for school in schools:
    list_forTmp = []  # Temporary list : Only used for conversion.
    enrollmentRate_tmp = 0
    employmentRate_tmp = 0
    students_number_tmp = 0
    students_female_number_tmp = 0
    students_moveOut_number_tmp = 0
    students_moveIn_number_tmp = 0
    generalTeacher_number_tmp = 0
    generalTeacher_female_number_tmp = 0
    schoolSize_tmp = 0
    generalClassNumber_tmp = 0
    specialClassNumber_tmp = 0

    for i in range(len(school)):
        
        if (i == 22) :
            # 취업률
            employmentRate_tmp = school[i]
            list_forTmp.append(employmentRate_tmp)
        
        if (i == 23) :
            # 진학률
            enrollmentRate_tmp = school[i]
            list_forTmp.append(enrollmentRate_tmp)
        
        if (i == 3) :
            # 학생 수
            students_number_tmp = school[i]

        if (i == 4) : 
            # 여학생 수 
            students_female_number_tmp = school[i]
        
        if (i == 5) : 
            # 전출
            students_moveOut_number_tmp = school[i]

        if (i == 6) : 
            # 전입
            students_moveIn_number_tmp = school[i]

        if (i == 7) :
            # 일반교사 수 
            generalTeacher_number_tmp = school[i]

        if (i == 8) : 
            # 여교사 수 
            generalTeacher_female_number_tmp = school[i]

        if (i == 11) :
            # 학교 면적
            schoolSize_tmp = school[i]

        if (i == 12) : 
            # 일반교실 수 
            generalClassNumber_tmp = school[i]

        if (i == 13) : 
            # 특수교실 수 
            specialClassNumber_tmp = school[i]
        
        
    # 인망률
    list_forTmp.append((1 - enrollmentRate_tmp) * (1 - employmentRate_tmp))
    
    # 학생 sexRatio
    list_forTmp.append(students_female_number_tmp / students_number_tmp)
    
    # 전체 학생 수 전출·입 비
    list_forTmp.append(students_moveOut_number_tmp / students_number_tmp)
    list_forTmp.append(students_moveIn_number_tmp / students_number_tmp)

    if (generalTeacher_number_tmp != 0):
        # 교사 sexRatio
        list_forTmp.append(generalTeacher_female_number_tmp / generalTeacher_number_tmp)
    
    # 학생 교사 ratio
    list_forTmp.append(generalTeacher_number_tmp / students_number_tmp)

    # 학생 남교사 ratio
    list_forTmp.append((generalTeacher_number_tmp - generalTeacher_female_number_tmp) / students_number_tmp)

    # 학생 여교사 ratio
    list_forTmp.append(generalTeacher_female_number_tmp / students_number_tmp)

    # 학생 특수교실 수 ratio
    list_forTmp.append(specialClassNumber_tmp / students_number_tmp)

    # 학생 학교 크기 ratio
    list_forTmp.append(schoolSize_tmp / students_number_tmp)

    # 학교 크기, 교실 수 ratio
    list_forTmp.append(schoolSize_tmp / (generalClassNumber_tmp + specialClassNumber_tmp))

    list_forLongLive.append(list_forTmp)



# 오만오천번정도

# 취업률
employmentRate = ContinuousVariable("employmentRate")
# 진학률
enrollmentRate = ContinuousVariable("enrollmentRate")
# 인망률
babyOneMoreTime = ContinuousVariable("babyOneMoreTime")
# 학생 sexRatio
students_sexRatio = ContinuousVariable("students_sexRatio")
# 전출·입 ratio
students_moveOut_ratio = ContinuousVariable("students_moveOut_ratio")
students_moveIn_ratio = ContinuousVariable("students_moveIn_ratio")
# 교사 sexRatio
generalTeacher_sexRatio = ContinuousVariable("generalTeacher_sexRatio")
# 학생 교사 ratio
students_generalTeacher_ratio = ContinuousVariable("students_generalTeacher_ratio")
# 학생 남교사 ratio
students_maleTeacher_ratio = ContinuousVariable("students_maleTeacher_ratio")
# 학생 여교사 ratio
students_femaleTeacher_ratio = ContinuousVariable("students_femaleTeacher_ratio")
# 학생 특수교실 수 ratio
students_specialClass_ratio = ContinuousVariable("students_specialClass_ratio")
# 학생 학교 크기 ratio
students_schoolSize_ratio = ContinuousVariable("students_schoolSize_ratio")
# 학교 크기, 교실 수 ratio
schoolSize_class_ratio = ContinuousVariable("schoolSize_class_ratio")




# set domain
domain = Domain([
                 employmentRate,
                 enrollmentRate, 
                 babyOneMoreTime, 
                 students_sexRatio, 
                 students_moveOut_ratio, 
                 students_moveIn_ratio, 
                 generalTeacher_sexRatio, 
                 students_generalTeacher_ratio, 
                 students_maleTeacher_ratio, 
                 students_femaleTeacher_ratio, 
                 students_specialClass_ratio, 
                 students_schoolSize_ratio, 
                 schoolSize_class_ratio
                 ])


# Final data
# Data seen at the output of the Python_Script widget.
out_data = Table(domain, list_forLongLive)
print(out_data)  # This line is only used for debugging, you can comment it.

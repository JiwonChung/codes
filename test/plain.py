from Orange.data import Table, Domain, ContinuousVariable
from pyodbc import connect

# "table1" from a MySQL server via ODBC connector.
connecteur = connect(DSN='allForYou')
cursur = connecteur.cursor()  # Creation of the cursor for data swept.

# Execution of the SQL Query.
cursur.execute("SELECT * FROM `forDataCompetition`.`schools`;")

# All data of "table1" are saved in "donnees".
schools = cursur.fetchall()


list_forLongLive = []  # Final list : It will contain the final data.
list_forTmp = []  # Temporary list : Only used for conversion.

# Whereas having tuples as list elements, you must convert them into lists included into the main list.
for school in schools:
    enrollmentRate_tmp = 0
    employmentRate_tmp = 0
    for i in range(len(school)):
        if (i == 1 or i == 18):
            continue
        else:
            if (i == 22) : 
                employmentRate_tmp = school[i]
            if (i == 23) : 
                enrollmentRate_tmp = school[i]
            list_forTmp.append(school[i])
    list_forTmp.append((1 - enrollmentRate_tmp) * (1 - employmentRate_tmp))
    list_forLongLive.append(list_forTmp)
    list_forTmp = []

index = ContinuousVariable("index")
schoolName_keyWord = ContinuousVariable("schoolName_keyWord")
students_number = ContinuousVariable("students_number")
students_female_number = ContinuousVariable("students_female_number")
students_moveOut_number = ContinuousVariable("students_moveOut_number")
students_moveIn_number = ContinuousVariable("students_moveIn_number")
generalTeacher_number = ContinuousVariable("generalTeacher_number")
generalTeacher_female_number = ContinuousVariable(
    "generalTeacher_female_number")
positionTeacher_number = ContinuousVariable("positionTeacher_number")
positionTeacher_female_number = ContinuousVariable(
    "positionTeacher_female_number")
schoolSize = ContinuousVariable("schoolSize")
numberOfDepartment = ContinuousVariable("numberOfDepartment")
generalClassNumber = ContinuousVariable("generalClassNumber")
specialClassNumber = ContinuousVariable("specialClassNumber")
regionalAverageIncome = ContinuousVariable("regionalAverageIncome")
regionalPricePerPy = ContinuousVariable("regionalPricePerPy")
regionalPriceMomentum = ContinuousVariable("regionalPriceMomentum")
book_total = ContinuousVariable("book_total")
book_yearlyUser = ContinuousVariable("book_yearlyUser")
book_yearlyBook = ContinuousVariable("book_yearlyBook")
employmentRate = ContinuousVariable("employmentRate")
enrollmentRate = ContinuousVariable("enrollmentRate")
babyOneMoreTime = ContinuousVariable("babyOneMoreTime")

domain = Domain([index,
                 schoolName_keyWord,
                 students_number,
                 students_female_number,
                 students_moveOut_number,
                 students_moveIn_number,
                 generalTeacher_number,
                 generalTeacher_female_number,
                 positionTeacher_number,
                 positionTeacher_female_number,
                 schoolSize,
                 numberOfDepartment,
                 generalClassNumber, 
                 specialClassNumber, 
                 regionalAverageIncome,
                 regionalPricePerPy,
                 regionalPriceMomentum,
                 book_total,
                 book_yearlyUser,
                 book_yearlyBook,
                 employmentRate,
                 enrollmentRate, 
                 babyOneMoreTime
                 ])


# Final data
out_data = Table(domain, list_forLongLive) # Data seen at the output of the Python_Script widget.
print(out_data) # This line is only used for debugging, you can comment it.

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



# Whereas having tuples as list elements, you must convert them into lists included into the main list.
for school in schools:
    list_forTmp = []  # Temporary list : Only used for conversion.
    studentsNumber = 0
    bookTotal = 0
    book_yearlyUsers = 0
    book_yearlyBooks = 0
    for i in range(len(school)):
        if (i == 1 or i == 16):
            continue
        else:
            list_forTmp.append(school[i])
            if (i == 3) :
                studentsNumber = school[i]
            if (i == 17) : 
                bookTotal = school[i]
            if (i == 18) : 
                book_yearlyBooks = school[i]
            if (i == 19) :
                book_yearlyUsers = school[i]

    list_forTmp.append(bookTotal / studentsNumber)
    list_forTmp.append(book_yearlyUsers / studentsNumber)
    list_forTmp.append(book_yearlyBooks / studentsNumber)
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
regionalAverageIncome = ContinuousVariable("regionalAverageIncome")
regionalPricePerPy = ContinuousVariable("regionalPricePerPy")
regionalPriceMomentum = ContinuousVariable("regionalPriceMomentum")
book_total = ContinuousVariable("book_total")
book_yearlyUser = ContinuousVariable("book_yearlyUser")
book_yearlyBook = ContinuousVariable("book_yearlyBook")
employmentRate = ContinuousVariable("employmentRate")
enrollmentRate = ContinuousVariable("enrollmentRate")

#
bookTotal_studentRatio = ContinuousVariable("bookTotal_studentRatio")
book_yearlyUser_studentRatio = ContinuousVariable("book_yearlyUser_studentRatio")
book_yearlyBook_studentRatio = ContinuousVariable("book_yearlyBook_studentRatio")
#
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
                 regionalAverageIncome,
                 regionalPricePerPy,
                 regionalPriceMomentum,
                 book_total,
                 book_yearlyUser,
                 book_yearlyBook,
                 employmentRate,
                 enrollmentRate, 
                 bookTotal_studentRatio, 
                 book_yearlyUser_studentRatio, 
                 book_yearlyBook_studentRatio
                 ])


# Final data
out_data = Table(domain, list_forLongLive) # Data seen at the output of the Python_Script widget.
print(out_data) # This line is only used for debugging, you can comment it.

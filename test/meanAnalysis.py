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
    employmentRate = 0
    enrollmentRate = 0
    schoolNameKeyWord_tmp = -1

    for i in range(len(school)) :
        if (i == 22) :
            # 취업률
            employmentRate = school[i]
        
        if (i == 23) :
            # 진학률
            enrollmentRate = school[i]
        
        if (i == 2) : 
            # 학교 이름 키워드 
            schoolNameKeyWord_tmp = school[i]
        
    if (schoolNameKeyWord_tmp == 1) : 
        list_forTmp.append(employmentRate)
        list_forTmp.append(enrollmentRate)
        list_forLongLive.append(list_forTmp)
    



# 오만오천번정도

# 취업률
employmentRate = ContinuousVariable("employmentRate")
# 진학률
enrollmentRate = ContinuousVariable("enrollmentRate")




# set domain
domain = Domain([
                 employmentRate,
                 enrollmentRate, 
                 ])


# Final data
# Data seen at the output of the Python_Script widget.
out_data = Table(domain, list_forLongLive)
print(out_data)  # This line is only used for debugging, you can comment it.

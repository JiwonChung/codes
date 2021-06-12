import requests
from requests.structures import CaseInsensitiveDict

url = "https://api.zoom.us/v2/users/jimmychung00@naver.com"

headers = CaseInsensitiveDict()
headers["Authorization"] = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOm51bGwsImlzcyI6InJRNkRwNU5LU29lc2c4cjBOSHNnZEEiLCJleHAiOjE2MjQwNjc5MDksImlhdCI6MTYyMzQ2MzEwOX0.tznDK91Vkax2_caqbrlDOAmBfYMhx0M-y6ZnlObiljg"


resp = requests.get(url, headers=headers)

print(resp.status_code)
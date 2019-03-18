import requests

file = requests.get('https://automatetheboringstuff.com/files/rj.txt')
if file.status_code == 200:
    print('Downloaded')
    print(len(file.text)) #for test purposes
    print(file.text[:40])

file.raise_for_status() #could use it instead of if###status_code == 200
import requests

r = requests.get('https://github.com')
#print(dir(r))
#print(help(r))
source = r.text

r = requests.get('https://github.com/fluidicon.png')
with open('pic.png', 'wb') as f:
    f.write(r.content)

print(r.ok)
print(r.headers)
print("-------------------------------------")
###########################################################

payload = {'page': 2, 'count': 25}
r = requests.get('https://httpbin.org/get', params = payload)
print(r.url)

payload = {'username': 'something', 'password': 'pass'}
r = requests.post('https://httpbin.org/post', data = payload)
r_dict = r.json()
print(r_dict['form'])

r = requests.get('https://httpbin.org/basic-auth/somename/somepass', auth=('somename', 'somepass'))
print(r.text)
r = requests.get('https://httpbin.org/basic-auth/somename/somepass', auth=('somename', 'wrongpass'), timeout = 3)
print(r)
#r = requests.get('https://httpbin.org/delay/4', timeout=3)
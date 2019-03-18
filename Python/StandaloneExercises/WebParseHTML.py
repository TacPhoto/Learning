import bs4, requests

res = requests.get('http://tacphoto.pl/blog/') #download page code
res.raise_for_status() #check exception

soup = bs4.BeautifulSoup(res.text, 'html.parser') #parse html
#print(soup) #test
titles = soup.select('h2') #parse h2 headers from the page

for i in range(len(titles)): #show titles
    print(titles[i].text.strip())
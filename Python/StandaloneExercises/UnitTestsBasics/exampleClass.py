import requests

class SomeClass:

    val = 10

    def __init__(self, x, y, name):
        self.x = x
        self.y = y
        self.name = name

    @property
    def email(self):
        return '{}.{}@sth.sth'.format(self.x, self.y)

    @property
    def values (self):
        return '{} {} {}'.format(self.x, self.y, self.name)

    def addValToX(self):
        self.x = int(self.x) + self.val

    def request(self):
        r = requests.get('https://httpbin.org/status/sth')
        if r.ok:
            return r.text
        else:
            return 'Bad Response!'
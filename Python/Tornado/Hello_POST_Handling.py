from abc import ABC

import tornado.httpserver
import tornado.ioloop
import tornado.web


class MainHandler(tornado.web.RequestHandler, ABC):
    """
    POST and POST/GET params handling
    """

    def get(self):
        resp = u'<form action="./" method="post"><input type="text" name="name" /></form>'
        self.write(resp)

    def post(self):
        name = self.get_argument('name')
        self.write(u'Hello ' + name)


class UrlHandler(tornado.web.RequestHandler, ABC):
    """
    Maps url using regex
    """

    def get(self, slug, id):
        resp = 'Slug: %s | ID: %s' % (slug, id)
        self.write(resp)


application = tornado.web.Application([
    (r"/", MainHandler),
    (r'/foo/(?P<slug>[\w\-_]+)/(?P<id>[0-9]+)/', UrlHandler),

])

if __name__ == "__main__":
    http_server = tornado.httpserver.HTTPServer(application)
    http_server.listen(8888)
    tornado.ioloop.IOLoop.instance().start()

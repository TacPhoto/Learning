import tornado.ioloop
import tornado.web

class basicRequestHandler(tornado.web.RequestHandler):
    def get(self):
        self.write("Hello Tornado!")

class staticRequestHandler(tornado.web.RequestHandler):
    def get(self):
        self.render("index.html")

class queryStringRequestHandler(tornado.web.RequestHandler):
    def get(self):
        n = int(self.get_argument("n"))
        result = "odd" if n % 2 else "even"

        self.write("It's " + result)

class resourceRequestHandler(tornado.web.RequestHandler):
    def get(self, id):
        self.write("Querying tweet #" + id)

if __name__ == "__main__":
    app = tornado.web.Application([
        (r"/", basicRequestHandler),
        (r"/page", staticRequestHandler),
        (r"/isEven", queryStringRequestHandler),
        ("/tweet/([0-9]+)", resourceRequestHandler)

    ])

    port = 10005
    app.listen(port)

    ioloop = tornado.ioloop.IOLoop.current()
    ioloop.start()

    print("Listening at port " + port)
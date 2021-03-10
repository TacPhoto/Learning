import tornado.httpclient
import tornado.ioloop
import tornado.web
import asyncio

def asynchronous_fetch(url):
    http_client = tornado.httpclient.AsyncHTTPClient()

    def handle_response(response):
        pass

    http_client.fetch(url)


async def fetch_coroutine(url):
    http_client = tornado.httpclient.AsyncHTTPClient()
    response = await http_client.fetch(url)
    return response.body


async def parallel_fetch(url1, url2):
    http_client = tornado.httpclient.AsyncHTTPClient()

    futures = [http_client.fetch(url1), http_client.fetch(url2)]

    resp1 = await futures[1]
    resp2 = await futures[2]

    return resp1, resp2


def main():
    """Construct and serve the tornado application."""
    port = 8888

    app = tornado.web.Application([

    ])


if __name__ == "__main__":
    try:
        loop = tornado.ioloop.IOLoop.current()
        loop.start()
    except Exception as e:
        pass
    finally:
        loop.close()
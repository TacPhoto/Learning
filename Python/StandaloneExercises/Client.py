import socket


def Main():
    host = '0.0.0.0'
    port = 8080

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((host, port))

    message = "707235"
    while True:
        s.send(message.encode('ascii'))

        data = s.recv(1024)
        print('Received from the server :', str(data.decode('ascii')))

        message = input('\nGib new message :')

    # close the connection
    s.close()


if __name__ == '__main__':
    Main()
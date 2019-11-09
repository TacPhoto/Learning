#based on Christian Thompson tutorial

import turtle
import winsound

window = turtle.Screen()
window.title("Pong by @tacphoto")
window.bgcolor("black")
window.setup(width=800, height=600)
window.tracer(0)

stretch_wid = 5
points_a = 0
points_b = 0


#Paddle A
paddle_a = turtle.Turtle()
paddle_a.speed(0)
paddle_a.shape("square")
paddle_a.shapesize(stretch_wid=stretch_wid, stretch_len=1)
paddle_a.color("white")
paddle_a.penup()
paddle_a.goto(-350, 0)


#Paddle B
paddle_b = turtle.Turtle()
paddle_b.speed(0)
paddle_b.shape("square")
paddle_b.color("white")
paddle_b.shapesize(stretch_wid=stretch_wid, stretch_len=1)
paddle_b.penup()
paddle_b.goto(350, 0)


#Ball
ball = turtle.Turtle()
ball.speed(0)
ball.shape("square")
ball.color("white")
ball.penup()
ball.goto(0, 0)
ball.dx = 2
ball.dy = 2


#Pen
pen = turtle.Turtle()
pen.speed(0)
pen.color("white")
pen.penup()
pen.hideturtle()
pen.goto(0, 260)


def paddle_a_up():
    if paddle_a.ycor() < 250:
        paddle_a.sety(paddle_a.ycor() + 20)

def paddle_b_up():
    if paddle_b.ycor() < 250:
        paddle_b.sety(paddle_b.ycor() + 20)

def paddle_a_down():
    if paddle_a.ycor() > -250:
        paddle_a.sety(paddle_a.ycor() - 20)

def paddle_b_down():
    if paddle_b.ycor() > -250:
        paddle_b.sety(paddle_b.ycor() - 20)

def bounceSound():
    winsound.PlaySound("bounce.wav", winsound.SND_ASYNC)

#Key binds
window.listen()
window.onkeypress(paddle_a_up, "w")
window.onkeypress(paddle_b_up, "Up")
window.onkeypress(paddle_a_down, "s")
window.onkeypress(paddle_b_down, "Down")

while True:
    window.update()

    pen.write("Player A: " + str(points_a) + "   " + "Player B: " + str(points_b), align="center",
              font=("Courier", 24, "normal"))

    #Ball movement
    ball.setx(ball.xcor() + ball.dx)
    ball.sety(ball.ycor() + ball.dy)


    #Border check
    if ball.ycor() > (window.window_height() / 2) - 10:
        ball.sety((window.window_height() / 2) - 10)
        ball.dy *= -1
        bounceSound()

    if ball.ycor() < (-window.window_height() / 2) + 10:
        ball.sety((-window.window_height() / 2) + 10)
        ball.dy *= -1
        bounceSound()

    if ball.xcor() > window.window_width() / 2:
        points_a +=1
        pen.clear()
        ball.goto(0, 0)
        ball.dx *= -1
        bounceSound()

    if  ball.xcor() < -window.window_width() / 2:
        points_b += 1
        pen.clear()
        ball.goto(0, 0)
        ball.dx *= -1
        bounceSound()

    # Paddle collision
    if (ball.xcor() > 340) and ball.ycor() < ((paddle_b.ycor() + stretch_wid * 10)) and (ball.ycor() > paddle_b.ycor() - 50):
        ball.dx *= -1
        ball.dy *= -1
        bounceSound()

    if (ball.xcor() < -340) and ball.ycor() < ((paddle_a.ycor() + stretch_wid * 10 )) and (ball.ycor() > paddle_a.ycor() - 50):
        ball.dx *= -1
        ball.dy *= -1
        bounceSound()

import numpy as np
from os import system, name

ROW_COUNT = 6
COL_COUNT = 7

def create_board():
    board = np.zeros((ROW_COUNT, COL_COUNT))
    return board

def drop_piece(board, row, col, player):
    board[row][col] = player

def is_valid_location(board, col):
    return board[ROW_COUNT - 1][col] == 0

def get_next_open_row(board, col):
    for row in range(ROW_COUNT):
        if board[row][col] == 0:
            return row

def action(board, col,  player):
    if is_valid_location(board, col):
        row = get_next_open_row(board, col)
        drop_piece(board, row, col, player)

def print_board(board):
    print(np.flip(board, 0))

def clear():
    if name == 'nt':
        _ = system('cls')
    else:
        _ = system('clear')

def is_winner(board, player): #not very efficient but easy to implement
    #horizontal check
    for col in range(COL_COUNT - 3):
        for row in range (ROW_COUNT):
            if board[row][col] == player and board[row][col + 1] == player and board[row][col + 2] == player and board[row][col + 3] == player:
                return True
    #vertical check
    for row in range(ROW_COUNT):
        for row in range(COL_COUNT - 3):
            if board[row][col] == player and board[row + 1][col] == player and board[row + 2][col] == player and board[row + 3][col] == player:
                return True
    #diagonal check
    for row in range(ROW_COUNT - 3):
        for row in range(COL_COUNT - 3):
            if board[row][col] == player and board[row + 1][col + 1] == player and board[row + 2][col + 2] == player and board[row + 3][col + 3] == player:
                return True

    for row in range(ROW_COUNT - 3):
         for row in range(3, COL_COUNT - 3):
             if board[row][col] == player and board[row - 1][col + 1] == player and board[row - 2][col + 2] == player and board[row - 3][col + 3] == player:
                 return True

board = create_board()
game_over = False
turn = 0

while not game_over:
    clear()

    #ask PL1 for input
    if turn == 0:
        col = int(input("Player 1 Make your Selection (0-6):"))
        action(board, col, "1")

    #ask PL2 for input
    else:
        col = int(input("Player 2 Make your Selection (0-6):"))
        action(board, col, "2")

    turn += 1
    turn = turn % 2

    print_board(board)

    if is_winner(board, 1):
        print_board("PLAYER 1 WINS")
        game_over = True
    if is_winner(board, 2):
        print_board("PLAYER 2 WINS")
        game_over = True
import os

board = {
    'top-l': '  ', 'top-m': '  ', 'top-r': '  ',
    'mid-l': '  ', 'mid-m': '  ', 'mid-r': '  ',
    'low-l': '  ', 'low-m': '  ', 'low-r': '  ',
}

def cls():
    os.system('cls' if os.name=='nt' else 'clear')
    print()

def printBoard(board):
    print(board['top-l'], '|', board['top-m'], '|', board['top-r'])
    print('------------')
    print(board['mid-l'], '|', board['mid-m'], '|', board['mid-r'])
    print('------------')
    print(board['low-l'], '|', board['low-m'], '|', board['low-r'])
def userInput():
    try:
        choice = input('Select: top/mid/low - l/m/r. Example: top-r for top right cell')
        board[choice] = ' X'
    except:
        print('Try again')

while(True):
    cls()
    printBoard(board)
    userInput()

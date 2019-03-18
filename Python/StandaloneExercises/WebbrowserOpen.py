import webbrowser, sys, pyperclip

#Check for command line arguments
if len(sys.argv) > 1:
    string = ' '.join(sys.argv[1:]) #create string based of arguments
else:
    string = pyperclip.paste()
string = 'perclip'
webbrowser.open('https://www.google.com/search?=' + string)
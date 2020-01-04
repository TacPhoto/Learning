import os

print(os.getcwd())

os.chdir(r'Z:/GitHubLearning/Learning/Python/')
print(os.getcwd())

print(os.listdir())

for dirpath, dirnames, filenames in os.walk(r'Z:/GitHubLearning/Learning/Python/'):
    print('Current path:', dirpath)
    print('Directories:', dirnames)
    print('Files:', filenames)
    print()

print(os.environ.get('HOME'))
print(dir(os.environ.get))
print(dir(os.environ))
print(dir(os))
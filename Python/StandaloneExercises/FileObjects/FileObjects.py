
f = open('test.txt', 'r')
print("F file| Name: " + f.name + " Mode: " + f.mode + "Is Closed: " + " " + str(f.closed))
f.close()
print("F file| Name: " + f.name + " Mode: " + f.mode + "Is Closed: " + " " + str(f.closed))
print("-------------------------------------------------------------------------------------")

with open('test.txt', 'r') as f:
    print("F file| Name: " + f.name + " Mode: " + f.mode + "Is Closed: " + " " + str(f.closed))

print("F file| Name: " + f.name + " Mode: " + f.mode + "Is Closed: " + " " + str(f.closed))
print("-------------------------------------------------------------------------------------")
#######

with open('test.txt', 'r') as f:
    f_contents = f.readlines()    #f_contents = f.readline()
    print(f_contents)

with open('test.txt', 'r') as f:
    for line in f:
        print(line, end='')
print("\n-------------------------------------------------------------------------------------")

with open('test.txt', 'r') as f:
    f_contents = f.read(15)
    print(f_contents, "###", end='')
    f_contents = f.read(9)
    print(f_contents, end='')
print("\n-------------------------------------------------------------------------------------")

with open('test.txt', 'r') as f:
    size_to_read = 5

    f_contents = f.read(size_to_read)

    while len(f_contents) > 0:
        print(f_contents, end='*')
        f_contents = f.read(size_to_read)
print("\n-------------------------------------------------------------------------------------")

with open('test.txt', 'r') as f:
    size_to_read = 5

    f_contents = f.read(size_to_read)
    print(f_contents, end='')

    f.seek(0)

    f_contents = f.read(size_to_read)
    print(f_contents, end='')
print("\n-------------------------------------------------------------------------------------")

with open ('write_test.txt', 'w') as f:
    f.write('test')
    f.write('test')
    f.write('\ntest')
    f.write(r'\ntest')
    f.seek(0)
    f.write('RR')
    f.seek(30)
    f.write('RR\n')

with open('test.txt', 'r') as rf:
    with open('copy_here.txt', 'w') as wf:
        for line in rf:
            wf.write(line)

with open('pic.png', 'rb') as rf:
    with open('copied_pic.png', 'wb') as wf:
        for line in rf:
            wf.write(line)

with open('pic.png', 'rb') as rf:
    with open('copied_pic2.png', 'wb') as wf:
        chunk_size = 4096
        rf_chunk = rf.read(chunk_size)
        while len(rf_chunk) > 0:
            wf.write(rf_chunk)
            rf_chunk = rf.read(chunk_size)
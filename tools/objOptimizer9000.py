import os
import re

ROOT_DIR = r"C:\Users\1\Desktop\WorkSpace2\Shaders-fixer\src\main\resources\assets\shaderfixer\models"

def procNum(num):
    try:
        if '.' in num:
            decP = num.split('.')[1]
            if len(decP) >= 6:
                r = f"{round(float(num), 5):.5f}"
                t = r.rstrip('0').rstrip('.')
                return t if t else "0"
        return num
    except ValueError:
        return num

def optObj(path):
    with open(path, 'r', encoding='utf-8', errors='ignore') as f:
        lines = f.readlines()
    nl = []
    for l in lines:
        m = re.match(r'^(v|vt|vn)\s+(.*)', l)
        if m:
            prefix = m.group(1)
            parts = m.group(2).split()
            optParts = [procNum(p) for p in parts]
            nl.append(f"{prefix} {' '.join(optParts)}\n")
        else:
            nl.append(l)
    with open(path, 'w', encoding='utf-8') as f:
        f.writelines(nl)

for root, dirs, files in os.walk(ROOT_DIR):
    for file in files:
        if file.lower().endswith('.obj'):
            path = os.path.join(root, file)
            optObj(path)
print("\n === D - O - N - E === ")
input("\n...")
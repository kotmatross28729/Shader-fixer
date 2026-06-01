#idontfuckingcareatthispoint

import os
import sys
import subprocess

ROOT_DIR = r"C:\Users\1\Desktop\WorkSpace2\Shaders-fixer\src\main\resources\assets\shaderfixer\models\doors"
BLENDER_PATH = r"C:\Program Files\Blender Foundation\Blender 3.4\blender.exe"

EDGE_THRESHOLD = 1.7

try:
    import bpy
    import bmesh
    IN_BLENDER = True
except ImportError:
    IN_BLENDER = False


def clear():
    bpy.ops.object.select_all(action='SELECT')
    bpy.ops.object.delete(use_global=False)

def processMesh(obj, threshold):
    bpy.context.view_layer.objects.active = obj
    bpy.ops.object.select_all(action='DESELECT')
    obj.select_set(True)
    #bpy.ops.object.transform_apply(location=False, rotation=False, scale=True)
    bpy.ops.object.mode_set(mode='EDIT')
    mesh = obj.data
    bpy.ops.mesh.select_all(action='SELECT')
    bpy.ops.mesh.tris_convert_to_quads()
    wasMod = False
    for _ in range(10):
        bm = bmesh.from_edit_mesh(mesh)
        bm.edges.ensure_lookup_table()
        edgesToSplit = [e for e in bm.edges if e.calc_length() >= threshold]
        if not edgesToSplit:
            break
        wasMod = True
        bmesh.ops.subdivide_edges(bm, edges=edgesToSplit, cuts=1, use_grid_fill=True)
        bmesh.update_edit_mesh(mesh)
    bm = bmesh.from_edit_mesh(mesh)
    bmesh.ops.triangulate(bm, faces=bm.faces)
    bmesh.update_edit_mesh(mesh)
    bpy.ops.object.mode_set(mode='OBJECT')
    return wasMod

def processFile(path):
    clear()
    bpy.ops.wm.collada_import(filepath=path)
    
    selected = [obj for obj in bpy.context.selected_objects if obj.type == 'MESH']
    if not selected:
        return False
    fileMod = False
    for obj in selected:
        obj.data.materials.clear()
        meshMod = processMesh(obj, EDGE_THRESHOLD)
        if meshMod:
            fileMod = True
    if not fileMod:
        return False
    bpy.ops.object.select_all(action='SELECT')
    
    bpy.ops.wm.collada_export(
        filepath=path,
        selected=True,
        export_mesh_type=0,
        include_children=True
    )
   
    #bpy.ops.wm.collada_export(
    #    filepath=path,
    #    selected=True,
    #    export_mesh_type=0,
    #    include_children=False
    #)
    
    return True

def run():
    for root, dirs, files in os.walk(ROOT_DIR):
        for file in files:
            if file.lower().endswith('.dae'):
                path = os.path.join(root, file)
                print(f"Processing: {path}")
                try:
                    changed = processFile(path)
                    if not changed:
                        print(f"Eliminating: {path}")
                        os.remove(path)
                except Exception as e:
                    print(f"Error while processing {file}: {e}")
    print("\n === D - O - N - E === ")

def main():
    if IN_BLENDER:
        run()
    else:
        blenderPath = BLENDER_PATH
        if not blenderPath or not os.path.exists(blenderPath):
            defPath = r"C:\Program Files\Blender Foundation\Blender 3.4\blender.exe"
            if os.path.exists(defPath):
                blenderPath = defPath
            else:
                print("BLENDER_PATH!!!!!!!")
                input("\n...")
                sys.exit(1)
        thiz = os.path.abspath(__file__)
        cmd = [blenderPath, "--background", "--python", thiz]
        print(f"Blender: {blenderPath}")
        process = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True, encoding='utf-8', errors='ignore')
        while True:
            output = process.stdout.readline()
            if output == '' and process.poll() is not None:
                break
            if output:
                print(output.strip())  
        input("\n...")

if __name__ == "__main__":
    main()

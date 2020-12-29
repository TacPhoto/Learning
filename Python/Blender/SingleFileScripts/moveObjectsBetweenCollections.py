#Blender 2.91
import bpy

#Collection setup
coll_from = bpy.data.collections['Collection']
coll_to = None

temp = None
for coll in bpy.data.collections:
    if coll.name == "Collection 2":
        coll_to = coll
        temp = True
        
coll_to = coll_to if temp else bpy.data.collections.new('Collection 2')
try:
    bpy.context.scene.collection.children.link(coll_to)
except:
    pass

#Move objects objects between collections
to_unlink = []

for ob in coll_from.objects:
    try:
        coll_to.objects.link(ob)
    except RuntimeError:
        pass
    to_unlink.append(ob)
    
for ob in to_unlink:
    coll_from.objects.unlink(ob)
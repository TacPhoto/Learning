#works with Blender 2.91
import bpy
from math import radians
from bpy.props import*

class SimpleOperator(bpy.types.Operator):
    """Tooltip"""
    bl_idname = "object.simple_operator"
    bl_label = "Some Example Operator"
    bl_options = {'REGISTER', 'UNDO'}
    
    noise_scale : FloatProperty(
        name = "Noise Scale",
        description = "Noise Texture Scale",
        default = 1.0,
        min = 0.0,
        max = 1.0
    )
    
    emission_val : FloatProperty(
        name = "Emission Strength",
        default = 500.0,
        min = 0.0,
        max = 10000.0
    )
    

    def execute(self, context):
        bpy.ops.mesh.primitive_cube_add()
        so = bpy.context.active_object

        # so.location[0] = 5
        # so.rotation_euler[0] += radians(45)

        #subsurf
        mod_subsurf = so.modifiers.new("Some Modifier", 'SUBSURF')
        mod_subsurf.levels = 3

        bpy.ops.object.shade_smooth()

        #displace
        mod_displace = so.modifiers.new("MyDisplacement", 'DISPLACE')

        #noise tex
        next_tex = bpy.data.textures.new("Texture", 'DISTORTED_NOISE')
        mod_displace.texture = next_tex
        next_tex.noise_scale = self.noise_scale

        #material
        new_mat = bpy.data.materials.new(name = "Some Material")
        so.data.materials.append(new_mat)
        new_mat.use_nodes = True
        nodes = new_mat.node_tree.nodes

        #material node setup
        material_output = nodes.get("Material Output")
        node_emission = nodes.new(type ='ShaderNodeEmission')

        node_emission.inputs[0].default_value = (0.0, 0.3, 1.0, 1) #color
        node_emission.inputs[1].default_value = 500.0 # strength

        links = new_mat.node_tree.links
        new_link = links.new(node_emission.outputs[0], material_output.inputs[0])
        
        return {'FINISHED'}


def register():
    bpy.utils.register_class(SimpleOperator)


def unregister():
    bpy.utils.unregister_class(SimpleOperator)


if __name__ == "__main__":
    register()

    # test call
    bpy.ops.object.simple_operator()

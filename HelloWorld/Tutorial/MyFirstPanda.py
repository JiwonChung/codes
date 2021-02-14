import sys
# base
import direct.directbase.DirectStart

from direct.showbase.DirectObject import DirectObject
from direct.showbase.ShowBaseGlobal import globalClock
from direct.showbase.InputStateGlobal import inputState
from direct.task.TaskManagerGlobal import taskMgr

from panda3d.core import AmbientLight
from panda3d.core import DirectionalLight
from panda3d.core import Vec3
from panda3d.core import Vec4
from panda3d.core import Point3
from panda3d.core import TransformState
from panda3d.core import BitMask32


from panda3d.bullet import BulletWorld
from panda3d.bullet import BulletPlaneShape
from panda3d.bullet import BulletBoxShape
from panda3d.bullet import BulletRigidBodyNode
from panda3d.bullet import BulletDebugNode


class MyFirstPanda(DirectObject):
    def __init__(self):
        super().__init__()
        base.setBackgroundColor(0.3, 0.2, 0.6, 1)
        base.setFrameRateMeter(True)

        base.cam.setPos(0, -20, 4)
        base.cam.lookAt(0, 0, 0)

        self.accept('escape', self.doExit)

        # Task
        taskMgr.add(self.update, 'updateWorld')

        self.setup()

    def doExit(self):
        self.cleanup()
        sys.exit(1)


    def update(self, task):
        dt = globalClock.getDt()

        self.processInput()
        # self.world.doPhysics(dt)
        self.world.doPhysics(dt, 5, 1.0 / 180.0)

        return task.cont

    def cleanup(self):
        self.world.removeRigidBody(self.groundNP.node())
        self.world.removeRigidBody(self.boxNP.node())
        self.world = None

        self.debugNP = None
        self.groundNP = None
        self.boxNP = None

        self.worldNP.removeNode()


    def setup(self):
        self.worldNP = render.attachNewNode('World')

        # World
        self.debugNP = self.worldNP.attachNewNode(BulletDebugNode('Debug'))
        self.debugNP.show()
        self.debugNP.node().showWireframe(True)
        self.debugNP.node().showConstraints(True)
        self.debugNP.node().showBoundingBoxes(False)
        self.debugNP.node().showNormals(True)

        # self.debugNP.showTightBounds()
        # self.debugNP.showBounds()

        self.world = BulletWorld()
        self.world.setGravity(Vec3(0, 0, -9.81))
        self.world.setDebugNode(self.debugNP.node())

        # Ground (static)
        shape = BulletPlaneShape(Vec3(0, 0, 1), 1)

        self.groundNP = self.worldNP.attachNewNode(BulletRigidBodyNode('Ground'))
        self.groundNP.node().addShape(shape)
        self.groundNP.setPos(0, 0, -2)
        self.groundNP.setCollideMask(BitMask32.allOn())

        self.world.attachRigidBody(self.groundNP.node())

        # Box (dynamic)
        shape = BulletBoxShape(Vec3(0.5, 0.5, 0.5))

        self.boxNP = self.worldNP.attachNewNode(BulletRigidBodyNode('Box'))
        self.boxNP.node().setMass(1.0)
        self.boxNP.node().addShape(shape)
        self.boxNP.setPos(0, 0, 2)
        # self.boxNP.setScale(2, 1, 0.5)
        self.boxNP.setCollideMask(BitMask32.allOn())
        # self.boxNP.node().setDeactivationEnabled(False)

        self.world.attachRigidBody(self.boxNP.node())

        visualNP = loader.loadModel('models/box.egg')
        visualNP.clearModelNodes()
        visualNP.reparentTo(self.boxNP)

    def processInput(self):
        force = Vec3(0, 0, 0)
        torque = Vec3(0, 0, 0)

        if inputState.isSet('forward'): force.setY(1.0)
        if inputState.isSet('reverse'): force.setY(-1.0)
        if inputState.isSet('left'):    force.setX(-1.0)
        if inputState.isSet('right'):   force.setX(1.0)
        if inputState.isSet('turnLeft'):  torque.setZ(1.0)
        if inputState.isSet('turnRight'): torque.setZ(-1.0)

        force *= 30.0
        torque *= 10.0

        force = render.getRelativeVector(self.boxNP, force)
        torque = render.getRelativeVector(self.boxNP, torque)

        self.boxNP.node().setActive(True)
        self.boxNP.node().applyCentralForce(force)
        self.boxNP.node().applyTorque(torque)


game = MyFirstPanda()
base.run()
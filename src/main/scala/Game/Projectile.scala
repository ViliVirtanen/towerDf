package Game

import scalafx.scene.paint.Color.Black

import scala.collection.mutable._


class Projectile(var loc: (Int,Int), world: World,val target: Enemy, damage: Int) extends GameObject(loc) {
  val color = Black
  val id: Char = 'p'
  var lastLocs = Buffer(loc)


    def destroy() = {
     world.currentProj.remove(world.currentProj.indexOf(this))
     world.currentObjects.remove(world.currentObjects.indexOf(this))
     this.loc = (98,98)

  }


  /** Checks the distance between enemy. If its long enough
   *  it calls moveTowards method moving it toward the target.
   *  if close enough, this projectile is destroyed.*/
  def update(): Unit = {
    val yd       = target.loc._1 - this.loc._1
    val xd       = target.loc._2 - this.loc._2
    val distance = math.sqrt(xd*xd + yd*yd)

    if (distance < 3 || target.loc == (98,98)) {
      lastLocs += loc
      this.destroy()

    } else {
      moveTowards()
      lastLocs += loc
    }

  }


/** Checks if x distance is bigger than y and moves move in x axis
 *  than y axis and vice versa. Also checks which direction to go. */
  def moveTowards() = {
  if (math.abs(target.loc._2 - this.loc._2) > math.abs((target.loc._1 - this.loc._1))) {
    if ((target.loc._1 - this.loc._1) < 0) {
      this.loc = (this.loc._1 -2 ,this.loc._2)
    } else {
      this.loc = (this.loc._1 +2 ,this.loc._2)
    }

    if ((target.loc._2 - this.loc._2) < 0) {
      this.loc = (this.loc._1 ,this.loc._2 -4)
    } else {
      this.loc = (this.loc._1 ,this.loc._2 +4)
    }

  } else {
    if ((target.loc._1 - this.loc._1) < 0) {
      this.loc = (this.loc._1 -4 ,this.loc._2)
    } else {
      this.loc = (this.loc._1 +4 ,this.loc._2)
    }

    if ((target.loc._2 - this.loc._2) < 0) {
      this.loc = (this.loc._1 ,this.loc._2 -2)
    } else {
      this.loc = (this.loc._1 ,this.loc._2 +2)
    }
  }
  }


}

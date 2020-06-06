# Java2DMinecraft
Added pseudo random world generation to the class: Math.random method would generate a terrain height and a grass block would be placed at those coords. Then dirt will be filled in underneath it. To make the terrain smoother, we limited terrain height differents to only one tile.
A basic form of psedo gravity was added as well. There is no version of acceleration in this version of the game, but Ahmer's upload should have a more up to date gravity system with acceleration.
Also updated collision detection to be more reliable; currently working on applying collision detection to block breaking and placing and makeing the new gravity system more reliable when player hits the ground.
Player animations were also added for walking direction and jumping; the new sprites can be found in the sprite sheet.
Input handling was refined
The game is currently in a very playable state. There is not much to do, but it is starting to feel like a 2D version of minecraft
Plans: get world interaction working; maybe add some form of inventory ui; maybe also add a hostile mob (zombie of something) that will deal damage if colliding with player. 

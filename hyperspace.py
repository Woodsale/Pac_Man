import pygame, sys, random, time

pygame.init()
pygame.font.init()
screen = pygame.display.set_mode((600,600))
pygame.display.set_caption("Hyperspace")

space1Image = pygame.image.load("Content\Image\space1.png")
space2Image = pygame.image.load("Content\Image\space2.png")
missleDownImage = pygame.image.load("Content\Image\missleDown.png")
missleUpImage = pygame.image.load("Content\Image\missleUp.png")
missleIconImage = pygame.image.load("Content\Image\missleIcon.png")
explosion1Image = pygame.image.load("Content\Image\explosion1.png")
explosion2Image = pygame.image.load("Content\Image\explosion2.png")
explosion3Image = pygame.image.load("Content\Image\explosion3.png")
explosion4Image = pygame.image.load("Content\Image\explosion4.png")
shipImage = pygame.image.load("Content\Image\ship.png")
healthIcon = pygame.image.load("Content\Image\health.png")
specialHealthIcon = pygame.image.load("Content\Image\specialHealth.png")
enemy1Image = pygame.image.load("Content\Image\enemy1.png")
enemy2Image = pygame.image.load("Content\Image\enemy2.png")
enemy3Image = pygame.image.load("Content\Image\enemy3.png")
laserImage = pygame.image.load("Content\Image\laser.png")
smallAesteroidImage = pygame.image.load("Content\Image\smallAsteroid.png")
mediumAesteroidImage = pygame.image.load("Content\Image\mediumAsteroid.png")

#laserSound = pygame.mixer.Sound('laser.wav')
class entity:
	"""Logic for all moving objects, from the players to projectiles"""
	def __init__(self, x, y, width, height):
		self.x = x
		self.y = y
		self.height = height
		self.width = width
	def collides(self, other):
		"""Checks if two entities collide with each other"""
		if ((self.x+self.width) >= other.x and self.x <= (other.x+other.width) and (self.y+self.height) >= other.y and self.y <= (other.y+other.height)):
			return True
		return False
class player(entity):
	"""Player stuff. Subclass of entity"""
	def __init__(self, health, rockets):
		entity.__init__(self, 275, 525, 50, 50)
		self.health = health
		self.rockets = rockets
	def blitPlayer():
		screen.blit(shipImage,[player1.x,player1.y])
		if (debug):
			pygame.draw.rect(screen,[255,0,0],[player1.x,player1.y,player1.width,player1.height],1)
			screen.blit(font1.render(("("+str(player1.x)+","+str(player1.y)+")"),1,(255,0,0)),[player1.x-45,player1.y-25])
	def gainHealth(self, amount):
		self.health += amount
		if (player1.health > 100):
			player1.health = 100
	def loseHealth(self, amount):
		self.health -= amount
		if (player1.health<1):
			#ADD SOMETHING HERE TO HAPPEN WHEN U LOSE
			sys.exit()
	def gainRocket(self):
		self.rockets += 1
		if (self.rockets > 3):
			self.rockets = 3
	def loseRocket(self):
		self.rockets -= 1
		if (self.rockets < 0):
			self.rockets = 0
class projectile(entity):
	"""For weapons such as rockets"""
	allProjectiles = []
	def __init__(self, x, y, width, height, speed, damage, doesExplode, canHurtEnemies, image):
		entity.__init__(self,x,y,width,height)
		self.speed = speed
		self.damage = damage
		self.doesExplode = doesExplode
		self.canHurtEnemies = canHurtEnemies
		self.image = image
		projectile.allProjectiles.append(self)
		#if (self.image == laserImage):
			#laserSound.play()
	def updateProjectiles():
		for i in projectile.allProjectiles:
			i.y += i.speed
			if (player1.collides(i)):
				player1.loseHealth(i.damage)
				if (i.doesExplode):
					explosion(i.x,i.y,40,40,0)
				projectile.allProjectiles.remove(i) 
			if (i.canHurtEnemies):
				for j in enemy.allEnemies:
					if (j.collides(i)):
						j.loseHealth(i.damage)
						if (i.doesExplode):
							explosion(i.x,i.y,40,40,0)
						projectile.allProjectiles.remove(i)
			if (i.y>800):
				projectile.allProjectiles.remove(i)
			if (i.y< -200 and i.speed <0):
				projectile.allProjectiles.remove(i)
	def blitProjectiles():
		for i in projectile.allProjectiles:
			screen.blit(i.image,[i.x,i.y])
			if (debug):
				pygame.draw.rect(screen,[255,0,0],[i.x,i.y,i.width,i.height],1)
				screen.blit(font1.render(("("+str(i.x)+","+str(i.y)+")"),1,(255,0,0)),[i.x-45,i.y-25])
class enemy(entity):
	"""Enemies such as ships or asteroids"""
	allEnemies = []
	def __init__(self,x,y,width,height,speed,damage,health,movement,weapons,image):
		entity.__init__(self,x,y,width,height)
		self.speed = speed
		self.damage = damage
		self.image = image
		self.movement = movement
		self.weapons = weapons
		self.timer = 0
		self.weaponsTimer = 0
		self.health = health
		self.max = health
		enemy.allEnemies.append(self)
	def loseHealth(self, amount):
		self.health -= amount
		if (self.health < 1):
			explosion(self.x,self.y,self.width,self.height,0)
			enemy.allEnemies.remove(self)
	def updateEnemies():
		for i in enemy.allEnemies:
			if (i.timer > 0):
				i.timer -= 1
			if (i.timer < 0):
				i.timer += 1
			
			if (i.movement == 0):
				#most basic AI: goes straight down. Usually used for asteroids
				i.y += i.speed
			if (i.movement == 1):
				#moves diagnally left
				i.y += i.speed
				i.x -= i.speed
			if (i.movement == 2):
				#moves diagnally right
				i.y += i.speed
				i.x += i.speed
			if (i.movement == 3):
				#strafes every now and then
				i.y += i.speed
				if (i.timer < 0):
					i.x -= 1
				if (i.timer > 0):
					i.x += 1
				elif (i.timer == 0 and random.randint(1,120)==50):
					if (random.randint(0,1)==1):
						i.timer = random.randint(40,120)
					else:
						i.timer = (-1)*random.randint(40,120)
			if (i.y+i.height >0):
				if (i.weaponsTimer > 0):
					i.weaponsTimer -= 1
				if (i.weaponsTimer <0):
					i.weaponsTimer += 1
				#if enemy is on screen...
				if (i.weapons == 1):
					#fires double laser every now and then
					if (i.weaponsTimer == 0):
						i.weaponsTimer = 150
						projectile(i.x+5,i.y+i.height,5,20,speed,5,False,False,laserImage)
						projectile(i.x+i.width-10,i.y+i.height,5,20,speed,5,False,False,laserImage)
				if (i.weapons == 2):
					#alternates left and right
					if (i.weaponsTimer==0):
						i.weaponsTimer = 150
						projectile(i.x+5,i.y+i.height,5,20,speed,5,False,False,laserImage)
					if (i.weaponsTimer == 75):
						projectile(i.x+i.width-10,i.y+i.height,5,20,speed,5,False,False,laserImage)
				if (i.weapons == 3):
					#only fires slow rockets
					if (i.weaponsTimer == 0 and random.randint(1,120)== 50):
						i.weaponsTimer = 200
						projectile(i.x+(i.width/2)-10,i.y+i.height,20,40,speed-1,25,True,False,missleDownImage)
				if (i.weapons == 4):
					#rapid fire in bursts
					if (i.weaponsTimer == 0):
						i.weaponsTimer = -150
					elif (i.weaponsTimer == -1):
						i.weaponsTimer = 150
					elif (i.weaponsTimer ==-150 or i.weaponsTimer ==-125 or  i.weaponsTimer ==-100 or  i.weaponsTimer ==-75 or  i.weaponsTimer ==-50 or i.weaponsTimer ==-25):
						projectile(i.x+5,i.y+i.height,5,20,speed,5,False,False,laserImage)
						projectile(i.x+i.width-10,i.y+i.height,5,20,speed,5,False,False,laserImage)
						

						
			if (player1.collides(i)):
				#if the ship collides with the player!
				explosion(i.x,i.y,i.width,i.height,0)
				player1.loseHealth(i.damage)
				enemy.allEnemies.remove(i)
			
			for j in enemy.allEnemies:
				if (j.collides(i) and j != i):
					temp = j.damage/2
					j.loseHealth(i.damage/2)
					i.loseHealth(temp)

			if (i.y>800):
				enemy.allEnemies.remove(i)

					

	def blitEnemies():
		for i in enemy.allEnemies:
			screen.blit(i.image,[i.x,i.y])
			if (i.health<i.max):
				healthBar(i.x, i.y-10,i.width, 10,i.health, i.max)
			if (debug):
				pygame.draw.rect(screen,[255,0,0],[i.x,i.y,i.width,i.height],1)
				screen.blit(font1.render(("("+str(i.x)+","+str(i.y)+"), "+str(i.weaponsTimer)),1,(255,0,0)),[i.x-45,i.y-25])
class explosion(entity):
	"""for explosions! (from projectiles)"""
	allExplosions = []
	def __init__(self,x,y,width, height,speed):
		entity.__init__(self,x,y,width,height)
		self.speed = speed
		self.status = 0 #status of the animation
		explosion.allExplosions.append(self)
	def updateExplosions():
		for i in explosion.allExplosions:
			i.y += i.speed
			if (random.randint(1,9)==5):
				i.status += 1
			if (i.status >= 8):
				explosion.allExplosions.remove(i)
	def blitExplosions():
		for i in explosion.allExplosions:
			if (i.status == 1 or i.status == 7):
				screen.blit(pygame.transform.scale(explosion1Image,(i.width,i.height)),[i.x,i.y])
			elif (i.status == 2 or i.status == 6):
				screen.blit(pygame.transform.scale(explosion2Image,(i.width,i.height)),[i.x,i.y])
			if (i.status == 3 or i.status == 5):
				screen.blit(pygame.transform.scale(explosion3Image,(i.width,i.height)),[i.x,i.y])
			if (i.status == 4):
				screen.blit(pygame.transform.scale(explosion4Image,(i.width,i.height)),[i.x,i.y])
			if (debug):
				screen.blit(font1.render(("("+str(i.status)+")"),1,(255,0,0)),[i.x-10,i.y-25])
class power(entity):
	"""For powerups, such as extra ammo or health. 1 is 25 hp, 2 is 1 rocket, 3 is full health"""
	allPowers = []
	def __init__(self,x,y,width,height,image, identity):
		entity.__init__(self,x,y,height,width)
		self.image = image
		self.identity = identity
		power.allPowers.append(self)
	def updatePowers():
		for i in  power.allPowers:
			i.y += speed/3
			if (i.collides(player1)):
				if (i.identity == 1):
					# health powerup
					player1.gainHealth(25)
				if (i.identity == 2):
					# rockets
					player1.gainRocket()
				if (i.identity == 3):
					#max health
					player1.gainHealth(100)
				power.allPowers.remove(i)
			if (i.y>800):
				power.allPowers.remove(i)
	def blitPowers():
		for i in power.allPowers:
			if (type(i.image) is list):
				screen.blit(i.image[random.randint(0,len(i.image)-1)],[i.x,i.y])
			else:
				screen.blit(i.image,[i.x,i.y])
			if (debug):
				pygame.draw.rect(screen,[255,0,0],[i.x,i.y,i.width,i.height],1)
				screen.blit(font1.render(("("+str(i.x)+","+str(i.y)+")"),1,(255,0,0)),[i.x-45,i.y-25])
def healthBar(x,y,width,height,current, max):
	pygame.draw.rect(screen, [180,0,0],[x,y,width,height],0)
	pygame.draw.rect(screen, [255,255,255],(x,y,width,height),1)
	pygame.draw.rect(screen, [0,180,0],[x+1,y+1,(current/max)*width-2,height-2],0)
def generate():
	global powerTick
	if (tick > 100 and random.randint(1,100)==45):
		ghost = random.randint(0,10)
		if (ghost > 8):
			#enemy
			ghost = random.randint(1,11)
			if (ghost <= 7):
				#basic ship
				ghost = random.randint(0,3)
				if (ghost == 1):
					#if it moves diagnally left...
					enemy(random.randint(500,700),-150,50,50,speed-2,20,15,1,random.randint(1,2),enemy1Image)
				elif (ghost == 2):
					#diagnally right...
					enemy(random.randint(-100,100),-150,50,50,speed-2,20,15,2,random.randint(1,2),enemy1Image)
				else:
					#straight or strafe
					enemy(random.randint(0,550),-150,50,50,speed-2,20,15,ghost,random.randint(1,2),enemy1Image)
			elif (ghost <= 9):
				#advanced ship
				ghost = random.randint(0,3)
				if (ghost == 1):
					#if it moves diagnally left...
					enemy(random.randint(500,700),-150,50,50,speed-2,20,25,1,3,enemy2Image)
				elif (ghost == 2):
					#diagnally right...
					enemy(random.randint(-100,100),-150,50,50,speed-2,20,25,2,3,enemy2Image)
				else:
					#straight or strafe
					enemy(random.randint(0,550),-150,50,50,speed-2,20,25,ghost,3,enemy2Image)
			elif (ghost == 10):
				#green ship
				ghost = random.randint(0,3)
				if (ghost == 1):
					#if it moves diagnally left...
					enemy(random.randint(500,700),-150,50,50,speed-2,25,25,1,4,enemy3Image)
				elif (ghost == 2):
					#diagnally right...
					enemy(random.randint(-100,100),-150,50,50,speed-2,25,25,2,4,enemy3Image)
				else:
					#straight or strafe
					enemy(random.randint(0,550),-150,50,50,speed-2,25,25,ghost,4,enemy3Image)
		elif (ghost == 8 and powerTick >3000):
			#powerup
			
			powerTick = 0
			ghost = random.randint(1,20)
			if (ghost <= 10):
				power(random.randint(0,570),-150,30,30,healthIcon,1)

			elif (ghost <= 18):
				power(random.randint(0,570),-150,30,30,missleIconImage,2)
			elif (ghost == 19):
				power(random.randint(0,570),-150,30,30,[healthIcon,specialHealthIcon],3)
		elif(ghost >=5):
			#astroids!
			ghost = random.randint(1,20)
			if (ghost <= 13):
				#mini
				enemy(random.randint(0,550),-150,50,50,speed-2,25,20,0,0,smallAesteroidImage)
			elif (ghost <= 19):
				#mid
				enemy(random.randint(0,500),-150,100,100,speed-2,40,30,0,0,mediumAesteroidImage)
			elif (ghost <= 20):
				#jumbo
				enemy(random.randint(0,450),-200,150,150,speed-2,50,40,0,0,pygame.transform.scale(mediumAesteroidImage,(150,150)))

		elif (ghost == 4):
			ghost = random.randint(1,5)
			if (ghost == 5):
				projectile(random.randint(0,580),-50,20,40,speed-1,25,True,False,missleDownImage)
			elif(ghost < 5):
				projectile(random.randint(0,595),-50,5,20,speed,5,False,False,laserImage)



font1 = pygame.font.SysFont("monospace", 15)

player1 = player(100,0)

background1 = 0
background2 = - 800

global speed
speed = 3

global debug
debug = False

global delay
delay = .002

global tick
tick = 0

powerTick = 0

moving = 0

#enemy(150,-1000,50,50,1,30,20,0,0,smallAesteroidImage)
#enemy(220,-1200,50,50,1,30,20,0,0,smallAesteroidImage)
#enemy(135,-1050,50,50,1,30,20,0,0,smallAesteroidImage)

#enemy(300,-50,50,50,1,20,15,3,3,enemy1Image)


#power(100,-500,30,30,missleIconImage,2)

prevLaser = False
					
while True:
	tick += 1
	powerTick += 1
	time.sleep(delay)
	player1.x += moving
	if (player1.x +player1.width > 600):
		player1.x = 600-player1.width
	if (player1.x < 0):
		player1.x = 0

	generate()

	background1 += speed
	background2 += speed
	if (background1>600):
		background1 = background2-800
	if (background2>600):
		background2 = background1-800

	projectile.updateProjectiles()
	explosion.updateExplosions()
	power.updatePowers()
	enemy.updateEnemies()

	screen.fill([255,255,255])
	screen.blit(space1Image, [0,background1])
	screen.blit(space2Image, [0,background2])
	
	player.blitPlayer()
	enemy.blitEnemies()

	explosion.blitExplosions()
	projectile.blitProjectiles()
	power.blitPowers()

	healthBar(10,10,100,30,player1.health,100)

	#rocket bar
	if (player1.rockets>0):
		screen.blit(missleIconImage,[125,10])
	if (player1.rockets>1):
		screen.blit(missleIconImage,[155,10])
	if (player1.rockets>2):
		screen.blit(missleIconImage,[185,10])
	

	if (debug):
		if (powerTick >=3000):
			screen.blit(font1.render(("powerTick: "+str(powerTick)),1,(0,255,0)),[10,575])
		elif (powerTick <3000):
			screen.blit(font1.render(("powerTick: "+str(powerTick)),1,(100,100,255)),[10,575])
		screen.blit(font1.render(("tick: "+str(tick)),1,(100,100,255)),[10,560])
		screen.blit(font1.render(("#explosions: "+str(len(explosion.allExplosions))),1,(100,100,255)),[10,545])
		if (len(projectile.allProjectiles)<10):
			screen.blit(font1.render(("#projectiles: "+str(len(projectile.allProjectiles))),1,(100,100,255)),[10,530])
		elif (len(projectile.allProjectiles)<20):
			screen.blit(font1.render(("#projectiles: "+str(len(projectile.allProjectiles))),1,(0,255,0)),[10,530])
		else:
			screen.blit(font1.render(("#projectiles: "+str(len(projectile.allProjectiles))),1,(255,100,100)),[10,530])
		screen.blit(font1.render(("#powers: "+str(len(power.allPowers))),1,(100,100,255)),[10,515])
		screen.blit(font1.render(("#enemies: "+str(len(enemy.allEnemies))),1,(100,100,255)),[10,500])
		screen.blit(font1.render(("speed: "+str(speed)),1,(100,100,255)),[10,485])
		screen.blit(font1.render(("prevLaser: "+str(prevLaser)),1,(100,100,255)),[10,470])
	pygame.display.flip()

	
	for event in pygame.event.get():
		if (event.type == pygame.QUIT):
			sys.exit()
		if (event.type == pygame.KEYDOWN):
			if (event.key == pygame.K_LEFT):
				moving = -1
			if (event.key == pygame.K_RIGHT):
				moving = 1

			if (event.key == pygame.K_SPACE):
				#laser firing
				if (prevLaser):
					projectile(player1.x+5,player1.y-20,5,20,(-1)*speed,5,False,True,laserImage)
					prevLaser = not prevLaser
				elif (not prevLaser):
					projectile(player1.x+player1.width-10,player1.y-20,5,20,(-1)*speed,5,False,True,laserImage)
					prevLaser = not prevLaser
			if (event.key == pygame.K_z and player1.rockets > 0):
				#rockets
				player1.loseRocket()
				projectile(player1.x+(player1.width/2)-10,player1.y-40,20,40,(-1)*speed+1,50,True,True,missleUpImage)
			if (event.key == pygame.K_F1):
				debug = not debug
			if (event.key == pygame.K_F2):
				if (delay == .002):
					delay = 1
				else:
					delay == .002
		if (event.type == pygame.KEYUP):
			if (event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT):
				moving = 0
			
			
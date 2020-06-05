# LifeOfAnts

In an Ant colony there are four different castes: Workers, Soldiers, Drones, and there is one Queen.

    For the sake of simplicity let they live on a grid. Ants have an actual ( x , y ) position 
    (initialize these within the limits of the colony, e.g. 100 steps). They change their positions 
    in each timestep, according to a caste-specific rule:
        1. The Queen (RED) sits in the origo and does not move.
          - All the Ants are aware of their distance from the Queen which is the number of steps
            needed to get to her (write a function for it!).
          - The Workers (GREEN) normally make one step randomly in one of the four directions.
          - The Soldiers (BLUE) normally just “patrol” close to their starting points; this means 
            that in a 4-cycle they step one towards North, then East, South, and West, and then they start the cycle again.
          - The Drones (PINK) always try to make one step towards the Queen. When they get 3 steps close, 
            they have a chance that the Queen is in the mood of mating. In this happy case they say “HALLELUJAH”, 
            stay there for 10 timesteps, and after that they are kicked off to a random point with the distance of 
            100 steps. If they do not have luck, they say “D’OH”, and are kicked 100 steps away instantly.
        2. The Queen’s mating mood is following this rule: after a successful mating she sets a countdown 
            timer (starting from some time between 100 and 200 timesteps) to get in the mood again. Tip: at
            first you can skip this part and set the Queen’s mood randomly.
        3. Extra feature: Sometimes a Wasp (YELLOW) appears in the colony. In this case all movements freeze 
            in the colony except that the Soldiers start to move towards the Wasp. The first Soldier to get 
            there says “HAJIME” and kills the intruder. All the ants continue with their normal activities.
        
        
        To compile: mvn javafx:compile
        To run:     mvn javafx:run
        
<img src="https://raw.githubusercontent.com/Kasia-Sikora/LifeOfAnts/master/src/main/resources/Screenshot.png?token=AMREBIFIY7RAGR6TCDMF56S63JIUQ" width="75%" height="75%">

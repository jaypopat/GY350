import discord
import os
import requests
import json

#portfolio dict is the main dict the key for portfolio dict is the message.author.id and the value is a different sub dictionary which is called user portfolio (this is further explored in the condition where the user doesnt have a portfolio and a portfolio is created) which contains coin and amount - coin being the key and amount being the value
my_secret = os.environ['token']
#intents = discord.Intents.default()
#intents.message_content = True
#client = discord.Client(intents = intents)
client = discord.Client()

portfolio_dict = {}
#to elaborate this idea further portfolio_dict -->userids -->((userportfolio dict elements) ---->keys(coin,amount,holding_value)) this is the route taken to access individual aspects of the main dictionary - portfolio_dict
prices = {}
#the function getCryptoPrices is used to fetch data and store it into the prices dict with coin being the key and the value to be the prices of the cryptocurrencies.

# This event will trigger when the bot is successfully connected to Discord
@client.event
async def on_ready():
    print('Logged in as')
    print(client.user.name)
    print(client.user.id)
    print('------')

def getCryptoPrices(crypto):
    URL = 'https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd'
    r = requests.get(url=URL)
    #on analysing/observing the api, I realised that the db fetched is a list of dictionaries
    db = r.json() 

    for coin in db: # coin refers to a dictionary in db - which is a list of dictionaries
        #coin is a dictionary whereas the price in the rhs is the key to access the price
        #the name is also a key of the coin dictionary which has a corresponding value of the crypto name
        prices[coin['name']] = coin['current_price']#name refers to coin name in api and current_price to the price
        #prices is a dictionary which accesses the coin dictionary which in turn using bracket notation accesses the coin_name key to retrieve the value as in the RHS the value is found out using the price as a key of the coin dictionary.
        #Hence this for loop iterating creates key-value pairs which are stored in the prices dictionary

    if crypto in prices.keys():
        return prices[crypto]
    else:
        return None


# This event will trigger when a message is sent in any of the server channels that the bot has access to
@client.event
async def on_message(message):
    if message.author ==client.user:
        return
    #making the code more readable
    msg = message.content
    msgSenderID = message.author.id

    if msg.startswith('!binance'):
        await message.channel.send('https://www.binance.com/en')
    if msg.startswith('!bitmart'):
        await message.channel.send('https://www.bitmart.com/')
    if msg.startswith('!cg'):
        await message.channel.send('https://www.coingecko.com/')
    if msg.startswith('!cmc'):
        await message.channel.send('https://coinmarketcap.com/')
    if msg.startswith('!'):
        # Split the message into command and arguments
        command, argument = message.content.split(' ', 1)

        if command == '!portfolio':
            # Check if the user has a portfolio
            if msgSenderID in portfolio_dict.keys():
                # Create a string to store the portfolio output
                portfolio_output_string = ''

                # Loop through the user's portfolio and add each holding to the output string
                for coin in portfolio_dict[msgSenderID].keys():
                    amount = portfolio_dict[msgSenderID][coin] # assigning coin to be key and amount to be value
                    portfolio_output_string += coin + ": " + str(amount) + '\n'
                await message.channel.send(portfolio_output_string)

            else:
                # If the user doesn't have a portfolio, inform them of this in a Discord message
                portfolio_dict[msgSenderID] = {}
                await message.channel.send("You don't have a portfolio yet, but I've created one for you!")

        elif command == '!add':
            
                coin, amount = argument.split(' ')
                amount = float(amount)

                # Check if the user has a portfolio
                if msgSenderID in portfolio_dict:
                    # Check if the user already has a holding of the specified coin
                    if coin not in portfolio_dict[msgSenderID].keys():
                        price = getCryptoPrices(coin)
                        #adding a key-value pair of the holding worth.This is updated and can be checked by the user for performance related reasons
                        portfolio_dict[msgSenderID][coin] = amount
                        value_key = amount * price
                        portfolio_dict[msgSenderID]['updating_holding_worth'] = value_key

                        if price is None:
                            await message.channel.send(coin+" is not a valid cryptocurrency")
                            return

                    else:
                        # Add a new holding to the user's portfolio
                        portfolio_dict[msgSenderID][coin] = amount
                else:
                    # If the user doesn't have a portfolio, create one and add the holding
                    
                    portfolio_dict[msgSenderID] = {}
                    
                # Inform the user that the holding was added to their portfolio
                await message.channel.send('Portfolio is created and holding added to your portfolio.')

                current_price = getCryptoPrices(coin)
            
                if current_price is not None:
                    holding_value = current_price * float(amount)
                    portfolio_dict[msgSenderID][coin]['holding_value'] = holding_value
                    await message.channel.send("Added " + amount + " " + coin + " to your portfolio.")
                else:
                    await message.channel.send(coin + ' is not a valid cryptocurrency.')
            

            # Handle the "!networth" command
        elif command == '!networth':
            # Check if the user has a portfolio
            if msgSenderID in portfolio_dict:
                # Create a variable to store the total net worth
                net_worth = 0
                for coin in portfolio_dict[msgSenderID]:
                    coin_price = getCryptoPrices(coin)
                    
                    if coin_price is not None:  #price retrieved from api
                        amount = portfolio_dict[msgSenderID][coin]
                        net_worth += amount * coin_price
                    await message.channel.send('Your total net worth is: ' +net_worth)
                    
                    if coin_price is None:
                        await message.channel.send(coin+" is not a valid cryptocurrency")
                    return
                
            else:
                # If the user doesn't have a portfolio, inform them of this in a Discord message
                await message.channel.send(
                    "You don't have a portfolio yet.To create a portfolio, use the !add command")
client.run(os.environ['token'])
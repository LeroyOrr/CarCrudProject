import pandas as pd

df = pd.read_csv('data.csv')
df.to_json('../data/data.json', orient='records', indent=4)
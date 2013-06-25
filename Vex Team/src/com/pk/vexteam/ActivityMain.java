package com.pk.vexteam;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMain extends Activity
{
	ActionBar actionBar;
	final List<DrawerItem> listOfItems = new ArrayList<DrawerItem>();
	
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	
	private ListView mDrawerList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		actionBar = getActionBar();
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		initializeNavigationDrawer();
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		// If the nav drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		// menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (mDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}
		
		switch (item.getItemId())
		{
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void initializeNavigationDrawer()
	{
		listOfItems.add(new DrawerItem("Page", "Dashboard"));
		listOfItems.add(new DrawerItem("Page", "Events"));
		listOfItems.add(new DrawerItem("Page", "Library"));

		DrawerAdapter adapter = new DrawerAdapter(ActivityMain.this, listOfItems);
		mDrawerList.setAdapter(adapter);
		
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.open, R.string.close)
		{
			public void onDrawerClosed(View view)
			{
				actionBar.setTitle(mTitle);
				invalidateOptionsMenu();
			}
			
			public void onDrawerOpened(View drawerView)
			{
				actionBar.setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			//
			Toast.makeText(ActivityMain.this, "Rawr", Toast.LENGTH_SHORT).show();
		}
	}
	
	public class DrawerAdapter extends BaseAdapter
	{
		private Context context;
		
		private List<DrawerItem> listItem;
		
		public DrawerAdapter(Context context, List<DrawerItem> listItem)
		{
			this.context = context;
			this.listItem = listItem;
		}
		
		public int getCount()
		{
			return listItem.size();
		}
		
		public Object getItem(int position)
		{
			return listItem.get(position);
		}
		
		public long getItemId(int position)
		{
			return position;
		}
		
		public View getView(int position, View view, ViewGroup viewGroup)
		{
			DrawerItem entry = listItem.get(position);
			if (view == null)
			{
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.drawer_item, null);
			}
			
			String Type = entry.getType();
			String Value = entry.getValue();
			
			// Page Item
			LinearLayout Page = (LinearLayout) view.findViewById(R.id.Page);
			LinearLayout Color = (LinearLayout) view.findViewById(R.id.Color);
			ImageView imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
			TextView textTitle = (TextView) view.findViewById(R.id.txtTitle);
			ImageView imgLock = (ImageView) view.findViewById(R.id.imgLock);
			
			
			if (Type.equals("Page"))
			{
				Page.setVisibility(View.VISIBLE);
				
				textTitle.setText(Value);
				imgLock.setVisibility(View.INVISIBLE);
				/*if (Value.equals("Dashboard"))
				{
					imgIcon.setImageResource(R.drawable.dashboard);
					Color.setBackgroundColor(context.getResources().getColor(R.color.holo_blue_dark2));
				}
				else if (Value.equals("Playlists"))
				{
					imgIcon.setImageResource(R.drawable.playlists);
					if (!Data.isProInstalled(context))
						imgLock.setVisibility(View.VISIBLE);
					else
						imgLock.setVisibility(View.GONE);
					Color.setBackgroundColor(context.getResources().getColor(R.color.holo_green_dark));
				}
				else if (Value.equals("Downloads"))
				{
					imgIcon.setImageResource(R.drawable.downloads);
					if (!Data.isProInstalled(context))
						imgLock.setVisibility(View.VISIBLE);
					else
						imgLock.setVisibility(View.GONE);
					Color.setBackgroundColor(context.getResources().getColor(R.color.holo_orange_dark));
				}*/
			}
			
			return view;
		}
	}
	
	public static class DrawerItem
	{
		String Type;
		String Value;
		
		public DrawerItem(String Type, String Value)
		{
			this.Type = Type;
			this.Value = Value;
		}
		
		public String getType()
		{
			return Type;
		}
		
		public String getValue()
		{
			return Value;
		}
	}
}

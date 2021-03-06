package user.domain.model.Service;

import android.content.Context;
import android.database.Cursor;

import com.example.servicesystem.infrastructure.Dao.Dao;

import java.util.ArrayList;
import java.util.List;

import project.infrastructure.db.Dao.DaoProject;
import user.domain.model.entity.ListBean;
import user.infrastructure.db.convert.ToBean;

public class ProjectService {
    private Dao dao;
    private DaoProject daoProject;

    public  ProjectService(Context context)
    {
        dao = new Dao(context);
        daoProject = new DaoProject(context);
    }

    public List<ListBean> getAll()
    {
        List<ListBean> list = new ArrayList<>();
        Cursor cursor = daoProject.getAllPros();
        if (cursor.getCount()!=0)
        {
           while (cursor.moveToNext())
           {
               String pid = cursor.getString(0);
               String pname = cursor.getString(1);
               String pstate = cursor.getString(2);
               String pintroduce =cursor.getString(3);
               String pcontent =cursor.getString(4);
               String ptype = cursor.getString(5);
               list.add(ToBean.toPmsg(pid,pname,pstate,pintroduce,pcontent,ptype));
           }
        }
        cursor.close();
        return list;
    }

    public List<ListBean> get(String id)
    {
        List<String> pids = dao.getPid(id);
        List<ListBean> list = new ArrayList<>();
        for(int i=0;i<pids.size();i++)
        {
            Cursor cursor = daoProject.getProjects(pids.get(i));
            if(cursor.getCount()!=0)
            {
                while (cursor.moveToNext())
                {
                    String pid = cursor.getString(0);
                    String pname = cursor.getString(1);
                    String pstate = cursor.getString(2);
                    String pintroduce =cursor.getString(3);
                    String pcontent =cursor.getString(4);
                    String ptype = cursor.getString(5);
                    list.add(ToBean.toPmsg(pid,pname,pstate,pintroduce,pcontent,ptype));
                }
            }
            cursor.close();
        }
        return list;
    }
}

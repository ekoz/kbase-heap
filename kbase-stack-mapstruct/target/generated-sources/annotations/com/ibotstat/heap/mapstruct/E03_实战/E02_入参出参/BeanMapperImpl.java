package com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参;

import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.entity.Dept;
import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.entity.Dept.DeptBuilder;
import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.vo.req.DeptReq;
import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.vo.resp.DeptResp;
import com.ibotstat.heap.mapstruct.E03_实战.E02_入参出参.vo.resp.DeptResp.DeptRespBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-17T14:08:41+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_201-2-redhat (Oracle Corporation)"
)
public class BeanMapperImpl implements BeanMapper {

    @Override
    public DeptResp entityToVO(Dept dept) {
        if ( dept == null ) {
            return null;
        }

        DeptRespBuilder deptResp = DeptResp.builder();

        deptResp.id( dept.getId() );
        deptResp.createUser( dept.getCreateUser() );
        deptResp.modifyUser( dept.getModifyUser() );
        if ( dept.getCreateDate() != null ) {
            deptResp.createDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( dept.getCreateDate() ) );
        }
        if ( dept.getModifyDate() != null ) {
            deptResp.modifyDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( dept.getModifyDate() ) );
        }
        deptResp.name( dept.getName() );
        deptResp.parent( entityToVO( dept.getParent() ) );

        return deptResp.build();
    }

    @Override
    public Dept voToEntity(DeptReq vo) {
        if ( vo == null ) {
            return null;
        }

        DeptBuilder dept = Dept.builder();

        dept.id( vo.getId() );
        dept.createUser( vo.getCreateUser() );
        dept.modifyUser( vo.getModifyUser() );
        if ( vo.getCreateDate() != null ) {
            dept.createDate( LocalDateTime.parse( vo.getCreateDate() ) );
        }
        if ( vo.getModifyDate() != null ) {
            dept.modifyDate( LocalDateTime.parse( vo.getModifyDate() ) );
        }
        dept.name( vo.getName() );

        return dept.build();
    }
}
